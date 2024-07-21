package me.androidbox.loginapp.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.authentication.ObserveAsEvents
import me.androidbox.designsystem.ui.theme.LoginAppTheme
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.authentication.presentation.login.LoginAction
import me.androidbox.authentication.presentation.login.LoginEvent
import me.androidbox.authentication.presentation.login.LoginScreen
import me.androidbox.authentication.presentation.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

data object LoginScreenRoot : Screen {

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val loginViewModel: LoginViewModel = koinViewModel()

        ObserveAsEvents(flow = loginViewModel.loginEvent) { loginEvent ->
            when(loginEvent) {
                is LoginEvent.OnLoginFailure -> {
                    Toast.makeText(context, loginEvent.error, Toast.LENGTH_LONG).show()
                }
                LoginEvent.OnLoginSuccess -> {
                    Toast.makeText(context, "You have successfully logged in", Toast.LENGTH_LONG).show()
                }

                LoginEvent.OnSignUpClicked -> {
                    Toast.makeText(context, "Not Implemented yet", Toast.LENGTH_LONG).show()
                }
            }
        }

        LoginScreen(
            loginState = loginViewModel.loginState,

            onLoginAction = { loginAction ->
                when(loginAction) {
                    LoginAction.OnLoginClicked -> {
                        keyboardController?.hide()
                        loginViewModel.onLoginAction(loginAction)
                    }
                    LoginAction.OnTogglePasswordVisibility -> {
                        loginViewModel.onLoginAction(loginAction)
                    }
                    LoginAction.OnSignUpClicked -> {
                        Toast.makeText(context, "Not Implemented", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }
}

@Composable
@Preview
fun PreviewLoginScreenRoot() {
    LoginAppTheme {
        LoginScreenRoot
    }
}