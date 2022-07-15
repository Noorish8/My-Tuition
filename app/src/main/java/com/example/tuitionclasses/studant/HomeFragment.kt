package com.example.tuitionclasses.studant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuitionclasses.MapsFragment
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.FragmentHomeBinding
import com.example.tuitionclasses.studant.ui.main.ClassDetailsActivity

class HomeFragment : Fragment() {
   lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imgEnglish.setOnClickListener {
           val intent=Intent(requireContext(),ClassDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.cardGrammer.setOnClickListener {
            val intent=Intent(requireContext(),ClassDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.cardPronouns.setOnClickListener {
            val intent=Intent(requireContext(),ClassDetailsActivity::class.java)
            startActivity(intent)
        }



    }


}