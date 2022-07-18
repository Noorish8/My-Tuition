package com.example.tuitionclasses.student.ui.main

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuitionclasses.databinding.ActivityClassDetailsBinding


class ClassDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityClassDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tuitionclasses.R.layout.activity_class_details)




        binding=ActivityClassDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val videoView = binding.videoView1
        videoView.setVideoPath("https://www.youtube.com/watch?v=0l69KEx7GQo")
        videoView.start()

//        val uri = Uri.parse("/ui/wp-content/uploads/2016/04/videoviewtestingvideo.mp4")
//        val simpleVideoView =
//            binding.videoView1 as VideoView // initiate a video view
//
//        simpleVideoView.setVideoURI(uri)
//        simpleVideoView.start()

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
        binding.imgMap.setOnClickListener {
//            val intent =Intent(this,MapsFragment::class.java)
//            startActivity(intent)

//            val mapFragment = supportFragmentManager.findFragmentById(
//                R.id.map
//            ) as? SupportMapFragment
//            mapFragment?.getMapAsync { googleMap ->
//              // addMarkers(googleMap)
//            }
        }
    }
}