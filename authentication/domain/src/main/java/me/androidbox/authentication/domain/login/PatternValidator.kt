package me.androidbox.authentication.domain.login

interface PatternValidator {
    fun matches(value: String): Boolean
}