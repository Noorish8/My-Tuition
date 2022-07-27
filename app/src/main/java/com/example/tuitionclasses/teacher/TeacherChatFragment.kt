package com.example.tuitionclasses.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.FragmentTeacherChatBinding
import com.example.tuitionclasses.user.intro.User
import com.example.tuitionclasses.user.intro.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class TeacherChatFragment : Fragment() {
    lateinit var binding: FragmentTeacherChatBinding
    lateinit var mAuth: FirebaseAuth
    lateinit var mDbRef: DatabaseReference
    private lateinit var adapter: UserAdapter
    private lateinit var userList:ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_teacher_chat, container, false)
        binding = FragmentTeacherChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        mAuth = FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(requireContext(), userList)
        // recyclerView = findViewById(R.id.recy_main_screen)
        binding.recyChatTeacher.layoutManager = LinearLayoutManager(requireContext())

        binding.recyChatTeacher.adapter = adapter
        //get inside this database and read the value

        mDbRef.child("Teacher").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                //the snapShort is particular schema database is
                for (postSnapshot in snapshot.children){
                    val currentUser =postSnapshot.getValue(User::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }

                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }


}