package com.example.userspgo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter(var context: Context,var userModelList: MutableList<UserModelItem>):
RecyclerView.Adapter<UserListAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var txtUserName: TextView
        var imgUserAvatar: CircleImageView

        init {
            txtUserName=itemView.findViewById(R.id.txtUserName)
            imgUserAvatar = itemView.findViewById(R.id.profileImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_user_item,parent,false))
    }

    override fun onBindViewHolder(holder: UserListAdapter.MyViewHolder, position: Int) {
       // holder.txtUserName.text= StringBuilder(userModelList[position].name).toString()
       // val user = userModelList[position]
      //  holder.txtUserName.text = StringBuilder(user.name).toString()
        val userModel = userModelList[position]
        holder.txtUserName.text = userModel.name

        // load image using Picasso
        userModel.imageUrl?.let { imageUrl ->
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground) // add placeholder image
                .into(holder.imgUserAvatar)
        }

    }

    override fun getItemCount(): Int {
        return userModelList.size
    }
}