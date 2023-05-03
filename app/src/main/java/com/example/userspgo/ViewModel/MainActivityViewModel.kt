package com.example.userspgo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.Repositories.MainRepo

class MainActivityViewModel:ViewModel() {

    private val mainRepo:MainRepo
    init{
        mainRepo= MainRepo()
    }
    val getUserList: LiveData<MutableList<UserModelItem>>
        get() = mainRepo.getUserModelLiveData

}