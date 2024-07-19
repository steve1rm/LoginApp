package me.androidbox.authentication.domain.di

import me.androidbox.authentication.domain.login.UserDataValidator
import me.androidbox.authentication.domain.login.usecases.LoginUserWithEmailAndPasswordUseCase
import me.androidbox.authentication.domain.login.usecases.imp.LoginUserWithEmailAndPasswordUseCaseImp
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationDomainModule = module {
    factoryOf(::UserDataValidator)



  /*  factory<LoginUserWithEmailAndPasswordUseCase> {
      //  LoginUserWithEmailAndPasswordUseCaseImp()
    }*/
}