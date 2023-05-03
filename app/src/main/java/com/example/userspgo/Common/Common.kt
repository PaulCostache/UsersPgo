package com.example.userspgo.Common

import com.example.userspgo.Network.APIService
import com.example.userspgo.Network.RetrofitClient

object Common {
    private const val BASE_URL="https://gorest.co.in/public/v2/"

    val getAPIService:APIService
        get() = RetrofitClient.getRetrofitClient(BASE_URL).create(APIService::class.java)
}