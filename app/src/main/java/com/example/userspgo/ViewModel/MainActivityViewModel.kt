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
            val data: MutableLiveData<MutableList<UserModelItem>> = MutableLiveData()
            mainRepo.getUserModelLiveData.observeForever { userList ->
                val activeUsers = userList.filter { it.status == "active" }.toMutableList() // filter out inactive users
                for (user in activeUsers) {
                    if (user.id % 2 != 0) {
                        user.imageUrl = "https://picsum.photos/200/200?random=${user.id}"
                    }else{
                        user.imageUrl = getInitialsImageUrl(user.name)
                    }
                }

                data.value = activeUsers
            }
            return data
        }
    private fun getInitialsImageUrl(name: String): String {
        val initials = name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
        return "https://via.placeholder.com/200x200.png?text=$initials"
        /*val updatedUserList: LiveData<List<UserModelItem>>
        get() {
            val data = MutableLiveData<List<UserModelItem>>()
            getUserList.observeForever { userList ->
                val updatedList = mutableListOf<UserModelItem>()
                userList.forEach { user ->
                    val initials = user.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
                    if (user.id % 2 == 0) {
                        updatedList.add(user.copy(initials = initials))
                    } else {
                        val imageUrl = "https://picsum.photos/200/200?random=${user.id}"
                        updatedList.add(user.copy(imageUrl = imageUrl))
                    }
                }
                data.value = updatedList
            }
            return data
        }*/
    }
}