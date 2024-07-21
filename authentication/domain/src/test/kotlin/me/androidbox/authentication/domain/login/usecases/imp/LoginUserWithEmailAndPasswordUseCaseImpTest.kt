package me.androidbox.authentication.domain.login.usecases.imp

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import me.androidbox.authentication.domain.login.UserValidationRepository
import me.androidbox.authentication.domain.utils.CheckResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.UUID

class LoginUserWithEmailAndPasswordUseCaseImpTest {

    private val userValidationRepository = mock<UserValidationRepository>()
    private lateinit var loginUserWithEmailAndPasswordUseCaseImp: LoginUserWithEmailAndPasswordUseCaseImp

    @BeforeEach
    fun setUp() {
        loginUserWithEmailAndPasswordUseCaseImp = LoginUserWithEmailAndPasswordUseCaseImp(userValidationRepository)
    }

    @Test
    fun `should login with correct email and password`() = runTest {
        // Arrange
        val email = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val data = UUID.randomUUID().toString()

        whenever(userValidationRepository.loginUser(email, password)).thenReturn(
            CheckResult.Success(data = data)
        )

        // Act
        val result = loginUserWithEmailAndPasswordUseCaseImp.execute(email, password)

        // Assert
        assertThat(result).isEqualTo(CheckResult.Success(data))
    }

    @Test
    fun `should not login with incorrect email and password`() = runTest {
        // Arrange
        val email = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()

        whenever(userValidationRepository.loginUser(email, password)).thenReturn(
            CheckResult.Failure()
        )

        // Act
        val result = loginUserWithEmailAndPasswordUseCaseImp.execute(email, password)

        // Assert
        assertThat(result).isEqualTo(CheckResult.Failure())
    }
}