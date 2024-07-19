package me.androidbox.authentication.data.login.repository

import me.androidbox.authentication.data.login.repository.remote.UserLoginRegisterRemoteDataSource
import me.androidbox.authentication.domain.login.UserValidationRepository
import me.androidbox.authentication.domain.utils.CheckResult

class UserValidationRepositoryImp(
    private val userLoginRegisterRemoteDataSource: UserLoginRegisterRemoteDataSource
) : UserValidationRepository {


    override suspend fun loginUser(email: String, password: String): CheckResult<String, Unit, Unit> {
        return userLoginRegisterRemoteDataSource.loginUser(email, password)
    }

    override suspend fun registerUser(email: String, password: String): CheckResult<String, Unit, Unit>{
        return userLoginRegisterRemoteDataSource.registerUser(email, password)
    }

    override suspend fun logout(): CheckResult<Unit, Unit, Unit> {
        return userLoginRegisterRemoteDataSource.logout()
    }

    override suspend fun isLoggedIn(): CheckResult<Boolean, Unit, Unit> {
        return userLoginRegisterRemoteDataSource.isLoggedIn()
    }
}
