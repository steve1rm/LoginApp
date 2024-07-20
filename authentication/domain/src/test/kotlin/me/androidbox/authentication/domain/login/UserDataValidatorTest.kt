package me.androidbox.authentication.domain.login

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class UserDataValidatorTest {

    private lateinit var userDataValidator: UserDataValidator

    @BeforeEach
    fun setup() {
        userDataValidator = UserDataValidator(
            patternValidator = object : PatternValidator {
                override fun matches(value: String): Boolean {
                    return true
                }
            }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "Test12345, true",
        "Test-1234, true",
        "test12345, false",
        "123456789, false",
        "TEST12345, false",
        "'', false",
        "'        ', false"
    )
    fun `should validate correct password`(password: String, expectedIsValid: Boolean) {
        // Act
        val passwordValidationState = userDataValidator.validatePassword(password)

        // Assert
        assertThat(passwordValidationState.isValidPassword).isEqualTo(expectedIsValid)
    }
}