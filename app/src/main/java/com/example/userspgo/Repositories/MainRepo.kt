package com.example.userspgo.Repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.userspgo.Common.Common
import com.example.userspgo.Model.PostModelItem
import com.example.userspgo.Model.UserModelItem
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
    /*fun getUserModelList(): MutableLiveData<MutableList<UserModelItem>> {
        val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData<MutableList<UserModelItem>>()
        compositeDisposable.add(apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { usersModels ->
                if (usersModels != null) {
                    data.value = usersModels
                }
            })
        return data
    }
    fun getUserPostsLiveData(userId: Int): MutableLiveData<MutableList<PostModelItem>> {
        val data: MutableLiveData<MutableList<PostModelItem>> = MutableLiveData<MutableList<PostModelItem>>()
        compositeDisposable.add(apiService.getUserPosts(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { postModels ->
                if (postModels != null) {
                    data.value = postModels
                }
            })
        return data
    }*/
    fun getUserModelList(): MutableLiveData<MutableList<UserModelItem>> {
        val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData<MutableList<UserModelItem>>()
        compositeDisposable.add(apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ usersModels ->
                if (usersModels != null) {
                    data.value = usersModels
                }
            }, { throwable ->
                // Handle network errors here
                throwable.printStackTrace()

                // Set an empty list as the data value to avoid NullPointerExceptions
                data.value = mutableListOf()
            }))
        return data
    }

    fun getUserPostsLiveData(userId: Int): MutableLiveData<MutableList<PostModelItem>> {
        val data: MutableLiveData<MutableList<PostModelItem>> = MutableLiveData<MutableList<PostModelItem>>()
        compositeDisposable.add(apiService.getUserPosts(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ postModels ->
                if (postModels != null) {
                    data.value = postModels
                }
            }, { throwable ->
                // Handle network errors here
                throwable.printStackTrace()
                // Set an empty list as the data value to avoid NullPointerExceptions
                data.value = mutableListOf()
            }))
        return data
    }
    fun clear() {
        compositeDisposable.clear()
    }
}