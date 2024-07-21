package me.androidbox.authentication.data.login

import android.util.Patterns
import me.androidbox.authentication.domain.login.PatternValidator

object EmailPatternValidatorImp : PatternValidator {

    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}