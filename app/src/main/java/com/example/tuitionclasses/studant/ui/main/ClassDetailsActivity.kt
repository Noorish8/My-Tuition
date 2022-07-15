package com.example.tuitionclasses.studant.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityClassDetailsBinding


class ClassDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityClassDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)


        binding=ActivityClassDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0123456789")
            startActivity(intent)

        }
        binding.imgUser.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")
                )
            )
        }
    }
}