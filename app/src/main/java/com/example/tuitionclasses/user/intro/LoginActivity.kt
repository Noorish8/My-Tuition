package com.example.tuitionclasses.user.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityLoginBinding
import com.example.tuitionclasses.student.ContentActivity
import com.example.tuitionclasses.teacher.TeacherActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        binding=ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

         val txtget =binding.txtGrt
        var bundle :Bundle ?=intent.extras
        var txtGrt =bundle!!.getString(Constant.userType)
        var srtUser = intent.getStringArrayExtra("")
        txtget.setText(txtGrt)


        binding.btnLogin.setOnClickListener {

            val email =binding.etEmail.text.toString()
            val paaWord =binding.etPassword.text.toString()

            val userPassWord =    binding.etPassword.text.toString().trim()
            if(email.isEmpty()){
                binding.etEmail.error="enter Email"
                binding.etEmail.requestFocus()
            } else if (userPassWord.isEmpty()) {
                binding.etPassword.error = "enter PassWord"
                binding.etPassword.requestFocus()
            }else{
                if (txtGrt=="Teacher"){
                    val intent = Intent(this,TeacherActivity::class.java)
                    startActivity(intent)
                }else{
                    val intent = Intent(this,ContentActivity::class.java)
                    startActivity(intent)
                }

            login(email,paaWord)
        }
            onBackPressed()

        }
    }

    private fun login(email:String,passWord:String){

        mAuth.signInWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                } else {

                    Toast.makeText(this, "user does not exist ",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }

    override fun onBackPressed(){
        finish()
    }
}