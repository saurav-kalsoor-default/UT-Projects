package com.getdefault.utprojects.viewmodels

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.getdefault.utprojects.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.IllegalArgumentException

class LoginViewModel(application : Application) : ViewModel() {

    private val userList : Array<User>
    private var userMap : HashMap<String, User>

    init {
        val gson = Gson()
        val arrayTutorialType = object : TypeToken<Array<User>>() {}.type
        val jsonString = application.assets.open("data.json").bufferedReader().use {it.readText()}

        userList = gson.fromJson(jsonString, arrayTutorialType)

        userMap = HashMap()
        for (user in userList){
            userMap[user.email] = user
        }
    }

    fun login(email : String, password: String) : User?{
        return if(userMap.containsKey(email) && userMap[email]?.password.equals(password)){
            userMap[email]
        }else{
            null
        }
    }

    fun isEmailValid(email : String) : Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password : String) : Boolean {
         return password.isNotEmpty() && password.length >= 6
    }


}

class LoginViewModelProvider(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(application) as T
        throw IllegalArgumentException("ViewModel cannot be created")
    }
}