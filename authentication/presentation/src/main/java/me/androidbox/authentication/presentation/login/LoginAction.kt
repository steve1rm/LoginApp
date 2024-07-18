package me.androidbox.authentication.presentation.login

sealed interface LoginAction {
    data object OnTogglePasswordVisibility : LoginAction
    data object OnLoginClicked : LoginAction
    data object OnRegisterClicked : LoginAction
}