package com.example.tuitionclasses.student

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.FragmentChatBinding
import com.example.tuitionclasses.user.intro.LoginActivity
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

    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentChatBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        mAuth = FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(requireContext(), userList)
       // recyclerView = findViewById(R.id.recy_main_screen)
       binding.recyMainScreen.layoutManager = LinearLayoutManager(requireContext())

        binding.recyMainScreen.adapter = adapter
        //get inside this database and read the value

        mDbRef.child("user").addValueEventListener(object: ValueEventListener{
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
        //there is different uid so get one by one addValueEventListener
        //exactly same otherwise isdn't work


    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)

            startActivity(intent)

            return true
        }
        return true
    }
}