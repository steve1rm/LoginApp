package me.androidbox.authentication.data.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import me.androidbox.authentication.data.login.EmailPatternValidatorImp
import me.androidbox.authentication.data.login.repository.UserValidationRepositoryImp
import me.androidbox.authentication.data.login.repository.remote.UserLoginRegisterRemoteDataSource
import me.androidbox.authentication.data.login.repository.remote.imp.UserLoginRegisterRemoteDataSourceImp
import me.androidbox.authentication.domain.login.PatternValidator
import me.androidbox.authentication.domain.login.UserValidationRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authenticationDataModule = module {

    single<PatternValidator> {
        EmailPatternValidatorImp
    }

    // factoryOf(::UserLoginRegisterRemoteDataSourceImp).bind<UserLoginRegisterRemoteDataSource>()

    single<FirebaseAuth> {
        Firebase.auth
    }

    factory<UserLoginRegisterRemoteDataSource> {
        UserLoginRegisterRemoteDataSourceImp(this.get<FirebaseAuth>())
    }

    factory<UserValidationRepository> {
        UserValidationRepositoryImp(get<UserLoginRegisterRemoteDataSource>())
    }
}
