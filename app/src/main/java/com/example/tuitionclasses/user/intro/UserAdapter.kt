package com.example.tuitionclasses.user.intro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tuitionclasses.R
import com.google.firebase.auth.FirebaseAuth

class UserAdapter (val context: Context, val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.userViewHolder>() {

    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.txt_user)
        val imageUser = itemView.findViewById<ImageView>(R.id.img_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_user_list, parent, false)
        return userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.name

        Glide.with(holder.itemView.getContext()).load(R.drawable.studantimg).centerCrop().into(holder.imageUser)//.transition(50)
//        holder.imageUser.setImageResource(currentUser.img!!)
//        Glide.with(context)
//            .load(R.drawable.studantimg)
//            .into(holder.imageUser);

//        Glide.with(context)
//            .load(R.drawable.studantimg)
//            .into(imageUser)

        holder.itemView.setOnClickListener {
            val intent =Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",  currentUser?.uid)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}