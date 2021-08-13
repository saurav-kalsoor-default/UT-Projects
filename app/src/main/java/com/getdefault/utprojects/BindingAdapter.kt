package com.getdefault.utprojects

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("userName")
fun bindName(tv : TextView, name : String){
    tv.text = "Name : $name"
}

@BindingAdapter("userEmail")
fun bindEmail(tv : TextView, email : String){
    tv.text = "Email : $email"
}

@BindingAdapter("userContact")
fun bindContact(tv : TextView, contact : String){
    tv.text = "Contact : $contact"
}

@BindingAdapter("userAge")
fun bindAge(tv : TextView, age : String){
    tv.text = "Age : $age"
}