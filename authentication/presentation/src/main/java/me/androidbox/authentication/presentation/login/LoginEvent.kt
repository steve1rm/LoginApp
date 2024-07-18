package me.androidbox.authentication.presentation.login

sealed interface LoginEvent {
    data object OnLoginSuccess : LoginEvent
    data class OnLoginFailure(val error: String) : LoginEvent
}