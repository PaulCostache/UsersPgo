package com.example.userspgo.Model

import android.os.Parcel
import android.os.Parcelable

data class PostModelItem(
    val body: String,
    val id: Int,
    val title: String,
    val user_id: Int
)