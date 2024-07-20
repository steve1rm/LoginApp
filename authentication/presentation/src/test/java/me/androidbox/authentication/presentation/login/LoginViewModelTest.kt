@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.authentication.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.androidbox.authentication.domain.login.UserDataValidator
import me.androidbox.authentication.domain.login.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.authentication.domain.utils.CheckResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID


class LoginViewModelTest {

    private val userDataValidator = mock<UserDataValidator>()
    private val loginUserWithEmailAndPasswordUseCase = mock<LoginUserWithEmailAndPasswordUseCase>()
    private lateinit var loginViewModel: LoginViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        loginViewModel = LoginViewModel(userDataValidator, loginUserWithEmailAndPasswordUseCase)
    }

    @Test
    fun `should login with correct email and password`() = runTest {
        // Arrange
        val email = loginViewModel.loginState.email.text.toString()
        val password = loginViewModel.loginState.password.text.toString()
        val data = UUID.randomUUID().toString()

        loginViewModel.loginState.email

        whenever(loginUserWithEmailAndPasswordUseCase.execute(email, password))
            .thenReturn(CheckResult.Success(data = data))

        // Act
        loginViewModel.onLoginAction(LoginAction.OnLoginClicked)

        // Assert
        verify(loginUserWithEmailAndPasswordUseCase).execute(email, password)
    }

    @Test
    fun `should not login with incorrect email and password`() = runTest {
        // Arrange
        val email = loginViewModel.loginState.email.text.toString()
        val password = loginViewModel.loginState.password.text.toString()
        val data = UUID.randomUUID().toString()

        loginViewModel.loginState.email

        whenever(loginUserWithEmailAndPasswordUseCase.execute(email, password))
            .thenReturn(CheckResult.Failure())

        // Act
        loginViewModel.onLoginAction(LoginAction.OnLoginClicked)

        // Assert
        verify(loginUserWithEmailAndPasswordUseCase).execute(email, password)
    }
}