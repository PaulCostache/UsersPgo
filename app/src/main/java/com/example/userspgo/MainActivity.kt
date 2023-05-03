package com.example.userspgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userspgo.Adapter.UserListAdapter
import com.example.userspgo.ViewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    var mainActivityViewModel:MainActivityViewModel?=null

    var recyclerUser:RecyclerView?=null
    var adapter: UserListAdapter?=null
    var layoutManager: LinearLayoutManager ?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel=ViewModelProvider(this)[MainActivityViewModel::class.java]

        recyclerUser=findViewById(R.id.recyclerUser)
        layoutManager = LinearLayoutManager(this)
        recyclerUser!!.layoutManager=layoutManager

        mainActivityViewModel!!.getUserList.observe(this) { userModels ->
            Log.e("MainActivity", "UserList: " + userModels.get(0).name)

            adapter= UserListAdapter(this,userModels)
            adapter!!.notifyDataSetChanged()
            recyclerUser!!.adapter=adapter
        }
    }
}