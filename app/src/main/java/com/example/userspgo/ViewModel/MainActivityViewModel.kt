package com.example.userspgo.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userspgo.Model.PostModelItem
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.Repositories.MainRepo

class MainActivityViewModel():ViewModel() {

    private val mainRepo:MainRepo
    init{
        mainRepo= MainRepo()
    }
    fun getUserList(): LiveData<MutableList<UserModelItem>> {
        val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData()
        mainRepo.getUserModelList().observeForever { userList ->
            val activeUsers = userList.filter { it.status == "active" }.toMutableList() // filter out inactive users
            for (user in activeUsers) {
                if (user.id % 2 != 0) {
                    user.imageUrl = "https://picsum.photos/200/200?random=${user.id}"
                } else {
                    user.imageUrl = getInitialsImageUrl(user.name)
                }
            }
            data.value = activeUsers
        }
        return data
    }
    fun getUserPosts(userId: Int): LiveData<MutableList<PostModelItem>> {
        val data: MutableLiveData<MutableList<PostModelItem>> = MutableLiveData()
        mainRepo.getUserPostsLiveData(userId).observeForever { postModels ->
            data.value = postModels
        }
        return data
    }
    private fun getInitialsImageUrl(name: String): String {
        val initials = name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
        return "https://via.placeholder.com/200x200.png?text=$initials"
    }
    override fun onCleared() {
        super.onCleared()
        mainRepo.clear()
    }
}