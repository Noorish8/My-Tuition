package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mObRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding= ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val paaWord = binding.etPassword.text.toString()
            signUp(name,email, paaWord)
        }

    }
    private fun signUp(name:String,email:String,passWord:String){
        mAuth.createUserWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    // Sign in succ
                    // ess, update UI with the signed-in user's information
                    finish()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this, "some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun addUserToDatabase(name: String, email: String, uid:String){
        mObRef= FirebaseDatabase.getInstance().getReference()
       mObRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}