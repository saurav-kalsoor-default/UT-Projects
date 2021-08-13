package com.getdefault.utprojects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name : String,
    val email : String,
    val password : String,
    val phone : String,
    val age : String
) : Parcelable