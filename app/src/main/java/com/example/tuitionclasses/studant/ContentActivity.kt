package com.example.tuitionclasses.studant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.tuitionclasses.R
import com.google.android.material.tabs.TabLayout

class ContentActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        // title = "KotlinApp"
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home_fill0_wght400_grad0_opsz48))
        tabLayout.addTab(tabLayout.newTab().setText("Student").setIcon(R.drawable.ic_baseline_person_24))
        tabLayout.addTab(tabLayout.newTab().setText("Chat").setIcon(R.drawable.ic_chat_fill0_wght400_grad0_opsz48))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ViewPagerAdapter(this, supportFragmentManager,
            tabLayout.tabCount)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}