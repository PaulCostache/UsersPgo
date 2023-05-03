package com.example.userspgo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.Repositories.MainRepo

class MainActivityViewModel:ViewModel() {

    private val mainRepo:MainRepo
    init{
        mainRepo= MainRepo()
    }
    /*val getUserList: LiveData<MutableList<UserModelItem>>
        get() = mainRepo.getUserModelLiveData*/
    val getUserList: LiveData<MutableList<UserModelItem>>
        get() {
            val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData<MutableList<UserModelItem>>()
            mainRepo.getUserModelLiveData.observeForever { userList ->
                //val activeUsers = userList.filter { it.status == "active" }.toMutableList() // filter out inactive users
                data.value = userList
            }

            return data
        }
}