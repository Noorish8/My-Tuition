package com.example.tuitionclasses.student

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.tuitionclasses.databinding.FragmentChatBinding
import com.example.tuitionclasses.student.ui.main.ChatAdapter
import com.example.tuitionclasses.student.ui.main.ChatModel
import com.example.tuitionclasses.user.intro.User
import com.example.tuitionclasses.user.intro.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ChatFragment : Fragment() {

  lateinit var binding:FragmentChatBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference
    private lateinit var adapter: UserAdapter
    private lateinit var userList:ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat, container, false)
        binding=FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mDbRef= FirebaseDatabase.getInstance().getReference()

        mAuth = FirebaseAuth.getInstance()

        userList= ArrayList()
        adapter= UserAdapter(requireContext(),userList)
        binding.recyMainScreen.adapter=adapter

//       val  userList= ArrayList<User>()
//           binding.recyUser.adapter=UserAdapter(this, userList)

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //previews list clear
                userList.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser =postSnapshot.getValue(User::class.java)
                    userList.add(currentUser!!)
                }
                //     adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.logout) {
//            mAuth.signOut()
//            val intent = Intent(requireContext(),LoginFragment::class.java)
//            finish()
//            startActivity(intent)
//
//            return true
//        }
//        return true
//    }

//        val imageList: ArrayList<ChatModel> = arrayListOf()
//        imageList.add(ChatModel("Arham",0))
//        imageList.add(ChatModel("Zaina",0))
//        imageList.add(ChatModel("Uzair",0))
//        imageList.add(ChatModel("Mariya",0))
//        imageList.add(ChatModel("Ibad",0))
//        imageList.add(ChatModel("Kinza",0))
//        binding.recyMainScreen.adapter=ChatAdapter(imageList)




    //}


}