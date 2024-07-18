package me.androidbox.authentication.data.di

import me.androidbox.authentication.data.login.EmailPatternValidatorImp
import me.androidbox.authentication.domain.login.PatternValidator
import org.koin.dsl.module

val authenticationDataModule = module {

    single<PatternValidator> {
        EmailPatternValidatorImp
    }
}
