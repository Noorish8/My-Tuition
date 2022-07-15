package com.example.tuitionclasses.studant.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityClassDetailsBinding

class ClassDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityClassDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)

        binding=ActivityClassDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}