package com.example.bsrdigicoin.model

import android.util.Patterns


class LoginUser(private val strEmailAddress: String, private val strPassword: String) {
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(strEmailAddress).matches()
    val isPasswordLengthGreaterThan5: Boolean
        get() = strPassword.length > 5

}