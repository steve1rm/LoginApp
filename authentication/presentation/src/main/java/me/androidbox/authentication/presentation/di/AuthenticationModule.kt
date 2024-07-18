package me.androidbox.authentication.presentation.di

import me.androidbox.authentication.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authenticationPresentationModule = module {
    viewModelOf(::LoginViewModel)
}