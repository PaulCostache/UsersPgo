package com.example.userspgo.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.userspgo.Common.Common
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.Model.UserModelX
import com.example.userspgo.Network.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepo {
    private val apiService:APIService
    private val compositeDisposable=CompositeDisposable()

    init {
        apiService=Common.getAPIService
    }
    val getUserModelLiveData: MutableLiveData<MutableList<UserModelItem>>
        get() {
            val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData<MutableList<UserModelItem>>()
            compositeDisposable.add(apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{usersModels->
                    if(usersModels!=null){
                        val activeUsers = usersModels.filter { it.status == "active" }.toMutableList() // filter out inactive users
                        data.value=activeUsers
                    }
                })
            return data
        }
}