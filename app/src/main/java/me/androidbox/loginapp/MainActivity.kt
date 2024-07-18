@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import me.androidbox.authentication.presentation.login.LoginScreen
import me.androidbox.authentication.presentation.login.LoginState
import me.androidbox.designsystem.ui.theme.LoginAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppTheme {
                LoginScreen(
                    loginState = LoginState(),
                    onLoginAction = {}
                )
            }
        }
    }
}
