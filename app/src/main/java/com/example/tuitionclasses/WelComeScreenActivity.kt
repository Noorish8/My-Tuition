package com.example.tuitionclasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuitionclasses.databinding.ActivityClassDetailsBinding
import com.example.tuitionclasses.databinding.ActivityTeacherBinding
import com.example.tuitionclasses.databinding.ActivityWelComeScreenBinding
import com.example.tuitionclasses.teacher.TeacherActivity
import com.example.tuitionclasses.user.intro.Constant
import com.example.tuitionclasses.user.intro.LoginActivity

class WelComeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityWelComeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wel_come_screen)

        binding= ActivityWelComeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTeacher.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            intent.putExtra(Constant.userType,"Teacher")
            startActivity(intent)
        }
        binding.btnStudent.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            intent.putExtra(Constant.userType,"Student")
            startActivity(intent)
        }

    }
}