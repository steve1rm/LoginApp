package me.androidbox.loginapp

import android.app.Application
import me.androidbox.authentication.data.di.authenticationDataModule
import me.androidbox.authentication.domain.di.authenticationDomainModule
import me.androidbox.authentication.presentation.di.authenticationPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LoginAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LoginAppApplication)
            modules(
                authenticationPresentationModule,
                authenticationDomainModule,
                authenticationDataModule
            )
        }
    }
}