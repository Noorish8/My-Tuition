package com.example.tuitionclasses.student.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tuitionclasses.R

class ChatAdapter(val list:ArrayList<ChatModel>):RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var name:TextView=itemView.findViewById(R.id.txt_name)
        var image: ImageView =itemView.findViewById(R.id.img_user)

        fun bind(model: ChatModel){
            name.text = model.name
            image.setImageResource(model.img)
            Glide.with(image)
                .load(R.drawable.studantimg)
                .into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_chat_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.bind(list[position])
    }

    override fun getItemCount(): Int {
      return list.size
    }
}