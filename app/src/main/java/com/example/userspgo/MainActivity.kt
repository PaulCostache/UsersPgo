package com.example.userspgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.userspgo.ViewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    var mainActivityViewModel:MainActivityViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel=ViewModelProvider(this)[MainActivityViewModel::class.java]

        mainActivityViewModel!!.getUserList.observe(this) { userModels ->
            Log.e("MainActivity", "UserList: " + userModels.get(0).name)
        }
    }
}