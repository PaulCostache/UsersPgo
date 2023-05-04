package com.example.userspgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userspgo.Adapter.UserListAdapter
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.ViewModel.MainActivityViewModel

class MainActivity : AppCompatActivity(),UserListAdapter.OnItemClickListener{

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

        mainActivityViewModel!!.getUserList().observe(this) { userModels ->
            Log.e("MainActivity", "UserList: " + userModels.get(0).name)

            adapter= UserListAdapter(this,userModels,this)
            adapter!!.notifyDataSetChanged()
            recyclerUser!!.adapter=adapter
        }
    }
    override fun onItemClick(userModel: UserModelItem) {
        val fragment = UserDetailsFragment.newInstance(userModel)
        supportFragmentManager.beginTransaction().apply {
            val container = findViewById<ViewGroup>(R.id.frament_test)
            container?.let {
                replace(container.id, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onBackPressed() {
        // do something here, for example, go back to the previous fragment
        supportActionBar?.title = resources.getString(R.string.MainActivityTitle)
        super.onBackPressed()
    }

}