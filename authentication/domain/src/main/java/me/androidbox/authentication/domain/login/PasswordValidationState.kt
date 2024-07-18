package me.androidbox.authentication.domain.login

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false
) {
    val isValidPassword: Boolean
        get() {
            return hasMinLength && hasNumber && hasLowerCaseCharacter && hasUpperCaseCharacter
        }
}

