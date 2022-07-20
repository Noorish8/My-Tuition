package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityDashBoardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashBoard : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
    lateinit var database:FirebaseDatabase
    lateinit var reference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference
    private lateinit var adapter: UserAdapter
    private lateinit var userList:ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= FirebaseDatabase.getInstance()
        reference =database.getReference("Users")

        binding.btnLogin.setOnClickListener {
            var name = binding.etName.text.toString()
            var email = binding.etEmail.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty()) {
                var model = User(name, email, uid = null)
                var id = reference.push().key

                reference.child(id!!).setValue(model)
                binding.etName.setText("")
                binding.etEmail.setText("")
            }
        }
    }
}


//        mDbRef= FirebaseDatabase.getInstance().getReference()
//
//        mAuth = FirebaseAuth.getInstance()
//
//        userList= ArrayList()
//        adapter= UserAdapter(this,userList)
//        binding.recyMainScreen.adapter=adapter
//
////       val  userList= ArrayList<User>()
////           binding.recyUser.adapter=UserAdapter(this, userList)
//
//        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                //previews list clear
//                userList.clear()
//                for (postSnapshot in snapshot.children){
//                    val currentUser =postSnapshot.getValue(User::class.java)
//                    userList.add(currentUser!!)
//                }
//                //     adapter.notifyDataSetChanged()
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.logout) {
//            mAuth.signOut()
//            val intent = Intent(this@DashBoard,LoginActivity::class.java)
//            finish()
//            startActivity(intent)
//
//            return true
//        }
//        return true
//    }
//  }
