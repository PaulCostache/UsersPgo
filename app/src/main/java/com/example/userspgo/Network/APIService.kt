package com.example.userspgo.Network

import android.database.Observable
import com.example.userspgo.Model.PostModelItem
import com.example.userspgo.Model.UserModelItem
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("users")
    fun getUsers(): io.reactivex.Observable<MutableList<UserModelItem>>
    @GET("users/{userId}/posts")
    fun getUserPosts(@Path("userId") userId: Int): io.reactivex.Observable<MutableList<PostModelItem>>
}