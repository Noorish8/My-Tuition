package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityDashBoardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashBoard : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
   // lateinit var database:FirebaseDatabase
    //lateinit var reference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var userList:ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(this, userList)
        recyclerView = findViewById(R.id.recy_main_screen)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        //get inside this database and read the value

        mDbRef.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                //the snapShort is particular schema database is
                for (postSnapshot in snapshot.children){
                    val currentUser =postSnapshot.getValue(User::class.java)
                    userList.add(currentUser!!)
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        //there is different uid so get one by one addValueEventListener
        //exactly same otherwise isdn't work
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            val intent = Intent(this@DashBoard,LoginActivity::class.java)
            finish()
            startActivity(intent)

            return true
        }
        return true
    }
}
