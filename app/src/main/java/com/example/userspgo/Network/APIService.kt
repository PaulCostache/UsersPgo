package com.example.userspgo.Network

import android.database.Observable
import com.example.userspgo.Model.UserModelItem
import retrofit2.http.GET

interface APIService {
    @GET("users")
    fun getUsers(): io.reactivex.Observable<MutableList<UserModelItem>>
}