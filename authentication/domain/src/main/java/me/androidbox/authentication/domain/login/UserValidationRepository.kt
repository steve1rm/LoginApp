package me.androidbox.authentication.domain.login

import me.androidbox.authentication.domain.utils.CheckResult

interface UserValidationRepository {
    suspend fun loginUser(email: String, password: String): CheckResult<String, Unit, Unit>
    suspend fun registerUser(email: String, password: String): CheckResult<String, Unit, Unit>
    suspend fun logout(): CheckResult<Unit, Unit, Unit>
    suspend fun isLoggedIn(): CheckResult<Boolean, Unit, Unit>
}
