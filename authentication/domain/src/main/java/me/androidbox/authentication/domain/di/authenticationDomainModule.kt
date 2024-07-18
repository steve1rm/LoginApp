package me.androidbox.authentication.domain.di

import me.androidbox.authentication.domain.login.UserDataValidator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authenticationDomainModule = module {
    factoryOf(::UserDataValidator)
}