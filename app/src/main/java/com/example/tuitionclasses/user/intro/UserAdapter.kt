package com.example.tuitionclasses.user.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitionclasses.R

class UserAdapter (val context: Context, val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.userViewHolder>() {

    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.txt_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_user_list, parent, false)
        return userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.name

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}