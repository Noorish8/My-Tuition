package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView:RecyclerView
    private lateinit var edittext:EditText
    private lateinit var sendButton:ImageView
    private lateinit var messageList:ArrayList<Message>
    private lateinit var messageAdapter:MessageAdapter
    private lateinit var mDbRef:DatabaseReference
    var receiverRoom: String? = null
    var senderRoom: String? = null

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val intent =Intent()
        val name =intent.getStringExtra("name")
        val receiverUid =intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        mDbRef = FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUid + senderUid
        receiverRoom =senderUid + receiverUid
        supportActionBar?.title =name

        chatRecyclerView =findViewById(R.id.recy_chat)
        edittext=findViewById(R.id.et_massageBox)
        sendButton=findViewById(R.id.img_send)

        messageList= ArrayList()
        messageAdapter = MessageAdapter(this,messageList)

        chatRecyclerView.layoutManager=LinearLayoutManager(this)
        chatRecyclerView.adapter=messageAdapter

        //logic for adding data to recyclerview
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children){
                        val message =postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        // adding the message to database
        binding.imgSend.setOnClickListener {

            val message = binding.etMassageBox.text.toString()
            val messageObject = Message(message,senderUid )
          //  push create unique node every time when this push will call
            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {

                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            binding.etMassageBox.setText("")
        }

    }
}