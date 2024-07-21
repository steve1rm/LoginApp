package me.androidbox.authentication.domain.login.usecases.imp

import me.androidbox.authentication.domain.login.UserValidationRepository
import me.androidbox.authentication.domain.login.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.authentication.domain.utils.CheckResult

class LoginUserWithEmailAndPasswordUseCaseImp(private val userValidationRepository: UserValidationRepository) :
    LoginUserWithEmailAndPasswordUseCase {

    override suspend fun execute(email: String, password: String): CheckResult<String, Unit, Unit> {
        return userValidationRepository.loginUser(email, password)
    }
}