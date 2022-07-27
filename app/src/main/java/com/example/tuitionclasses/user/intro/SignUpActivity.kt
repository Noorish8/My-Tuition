package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mObRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        binding= ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val passWord = binding.etPassword.text.toString()



            if (name==null || name== ""){
                Toast.makeText(this,"enter name",Toast.LENGTH_SHORT ).show()
            }else if (email==null || email== ""){
                Toast.makeText(this,"enter email ",Toast.LENGTH_SHORT ).show()
            }
            else if (passWord==null || passWord== ""){
                Toast.makeText(this,"enter passWord",Toast.LENGTH_SHORT ).show()
            }
            else{
            signUp(name,email, passWord)
            }
        }

    }
    private fun signUp(name:String,email:String,passWord:String){
       mAuth.createUserWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.v("SignUp_task", task.result.toString())
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    addUserToDatabaseTeacher(name,email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, LoginActivity::class.java)
                    finish()
                    startActivity(intent)
                    // Sign in succ
                    // ess, update UI with the signed-in user's information

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                    // what does task actually do
                }

            }
    }
    private fun addUserToDatabase(name: String, email: String, uid:String){
        mObRef= FirebaseDatabase.getInstance().getReference()
       mObRef.child("student").child(uid).setValue(User(name,email,uid,img = null))

    }
    private fun addUserToDatabaseTeacher(name: String, email: String, uid:String){
        mObRef= FirebaseDatabase.getInstance().getReference()
        mObRef.child("Teacher").child(uid).setValue(User(name,email,uid,img = null))

    }

}