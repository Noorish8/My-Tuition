package com.example.tuitionclasses.student.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.ActivityClassDetailsBinding


class ClassDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityClassDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_details)


        binding = ActivityClassDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//    val displayYoutubeVideo = binding.videoView1
//    displayYoutubeVideo.webViewClient = object : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            return false
//        }
//    }
//    val webSettings = displayYoutubeVideo.settings
//    webSettings.javaScriptEnabled = true
//    displayYoutubeVideo.loadUrl("https://www.youtube.com/watch?v=0l69KEx7GQo")


        val videoView = binding.videoView1
        try {
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            mediaController.setMediaPlayer(videoView)
            val videoURI = Uri.parse("https://www.youtube.com/watch?v=0l69KEx7GQo")
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(videoURI)
            videoView.start()
        }catch (e:Exception){
            Log.e("ClassDetailsActivity","video view exception$e")
        }

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