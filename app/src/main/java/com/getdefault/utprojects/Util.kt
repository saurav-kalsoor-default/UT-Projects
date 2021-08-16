package com.getdefault.utprojects

import android.util.Patterns

fun isEmailValid(email : String) : Boolean{
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password : String) : Boolean {
    return password.length > 6
}

fun isPhoneValid(phone : String) : Boolean {
    return phone.isNotEmpty() && phone.length == 10
}