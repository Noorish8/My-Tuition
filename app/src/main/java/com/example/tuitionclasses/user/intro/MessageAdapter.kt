package com.example.tuitionclasses.user.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitionclasses.R
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList:ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_RECEIVE =1
    val ITEM_SENT =2

    class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_send_msg)

    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentReceive = itemView.findViewById<TextView>(R.id.txt_receive_msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            //inflate receive
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.row_receive_msg, parent, false)
            return ReceiveViewHolder(view)

        }else{
            //inflate sent
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.row_send_msg, parent, false)
            return SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
       if (holder.javaClass == SendViewHolder::class.java){
           val viewHolder = holder as SendViewHolder
           holder.sentMessage.text = currentMessage.message
              // do stuff for sent view holder
       }else{
            // do stuff for receive view holder
           val viewHolder = holder as ReceiveViewHolder
           holder.sentReceive.text = currentMessage.message
       }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        //if uid of the current user matches with the sender id of this msg(currentMessage)
        //that means we have to inflate send viewholder
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderTd)){

          return ITEM_SENT
        }else{
                return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}