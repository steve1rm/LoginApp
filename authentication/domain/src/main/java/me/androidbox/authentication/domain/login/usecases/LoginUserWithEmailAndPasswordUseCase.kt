package me.androidbox.authentication.domain.login.usecases

import me.androidbox.authentication.domain.utils.CheckResult

fun interface LoginUserWithEmailAndPasswordUseCase {
    suspend fun execute(email: String, password: String): CheckResult<String, Unit, Unit>
}