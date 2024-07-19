@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.authentication.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.androidbox.authentication.domain.login.UserDataValidator
import me.androidbox.authentication.domain.login.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.authentication.domain.utils.CheckResult
import me.androidbox.authentication.domain.utils.DataError

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val loginUserWithEmailAndPasswordUseCase: LoginUserWithEmailAndPasswordUseCase
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    private val eventLoginChannel = Channel<LoginEvent>()
    val loginEvent = eventLoginChannel.receiveAsFlow()

    init {
        combine(loginState.email.textAsFlow(), loginState.password.textAsFlow()) { email, password ->
            val isValidEmail = userDataValidator.isValidEmail(email = email.toString().trim())

            loginState = loginState.copy(
                isValidEmail = isValidEmail,
                canLogin = isValidEmail && password.isNotEmpty()) /** using notEmpty as a 'space' could be a valid password character */
        }.launchIn(viewModelScope)
    }

    fun onLoginAction(action: LoginAction) {
        when(action) {
            LoginAction.OnLoginClicked -> {
                login()
            }

            LoginAction.OnTogglePasswordVisibility -> {
                loginState = loginState.copy(
                    isPasswordVisible = !loginState.isPasswordVisible
                )
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginState = loginState.copy(isLoggingIn = true)

            val result = loginUserWithEmailAndPasswordUseCase.execute(
                email = loginState.email.text.toString().trim(),
                password = loginState.password.text.toString()
            )

            loginState = loginState.copy(isLoggingIn = false)

            when(result) {
                is CheckResult.Failure -> {
                    /** Display toast message */
                    if(result.exceptionError == DataError.Network.UNAUTHORIZED) {
                        eventLoginChannel.send(LoginEvent.OnLoginFailure(result.exceptionError.toString()))
                    }
                    else {
                        eventLoginChannel.send(LoginEvent.OnLoginFailure(result.exceptionError.toString()))
                    }
                }
                is CheckResult.Success -> {
                    /** Go to home page */
                    eventLoginChannel.send(LoginEvent.OnLoginSuccess)
                }
            }
        }
    }

}