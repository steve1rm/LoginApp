package me.androidbox.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import me.androidbox.designsystem.ui.theme.LoginAppTheme
import me.androidbox.loginapp.navigation.LoginScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppTheme {
               Navigator(screen = LoginScreenRoot)
            }
        }
    }
}
