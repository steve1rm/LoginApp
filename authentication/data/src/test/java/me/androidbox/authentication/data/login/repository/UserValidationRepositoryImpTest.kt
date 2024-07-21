package me.androidbox.authentication.data.login.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import me.androidbox.authentication.data.login.repository.remote.UserLoginRegisterRemoteDataSource
import me.androidbox.authentication.domain.utils.CheckResult

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.UUID

class UserValidationRepositoryImpTest {

    private val userLoginRegisterRemoteDataSource = mock<UserLoginRegisterRemoteDataSource>()
    private lateinit var userValidationRepositoryImp: UserValidationRepositoryImp

    @BeforeEach
    fun setUp() {
        userValidationRepositoryImp = UserValidationRepositoryImp(userLoginRegisterRemoteDataSource)
    }

    @Test
    fun `should login user with correct email and password`() = runTest {
        // Arrange
        val email = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val data = UUID.randomUUID().toString()

        whenever(userLoginRegisterRemoteDataSource.loginUser(email, password)).thenReturn(
            CheckResult.Success(data = data)
        )

        // Act
        val result = userValidationRepositoryImp.loginUser(email, password)

        // Assert
        assertThat(result).isEqualTo(CheckResult.Success(data))
    }

    @Test
    fun `should not login user with incorrect email and password`() = runTest {
        // Arrange
        val email = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()

        whenever(userLoginRegisterRemoteDataSource.loginUser(email, password)).thenReturn(
            CheckResult.Failure()
        )

        // Act
        val result = userValidationRepositoryImp.loginUser(email, password)

        // Assert
        assertThat(result).isEqualTo(CheckResult.Failure())
    }
}