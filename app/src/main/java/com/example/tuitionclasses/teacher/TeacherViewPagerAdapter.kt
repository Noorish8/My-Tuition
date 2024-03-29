package com.example.tuitionclasses.teacher

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tuitionclasses.student.ChatFragment
import com.example.tuitionclasses.student.HomeFragment
import com.example.tuitionclasses.student.studentFragment

class TeacherViewPagerAdapter (
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                TeacherChatFragment()
            }
            2 -> {
                studentFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}