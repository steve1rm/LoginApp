package me.androidbox.authentication.presentation.login

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

class LoginScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @BeforeEach
    fun setup() {
        rule.setContent {
            LoginScreen(
                loginState = LoginState(),
                onLoginAction = { }
            )
        }
    }

    @Test
    fun should_enter_email() {
        val input = UUID.randomUUID().toString()

        rule.onNodeWithText("Email").assertExists()
        rule.onNodeWithTag("email").performTextInput(input)
        rule.onNodeWithText(input).isDisplayed()
        rule.onNodeWithText("Login").performClick()
    }


    @org.junit.Test
    fun should_enter_password() {
        // Act & Assert
        val input = UUID.randomUUID().toString()

        rule.onNodeWithText("Password").assertExists()
        rule.onNodeWithTag("password").performTextInput(input)
        rule.onNodeWithText(input).isDisplayed()
        rule.onNodeWithText("Login").performClick()
    }
}
