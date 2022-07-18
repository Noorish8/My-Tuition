package com.example.tuitionclasses.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuitionclasses.databinding.FragmentStudantBinding

class studentFragment : Fragment() {

    lateinit var binding: FragmentStudantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_studant, container, false)
        binding = FragmentStudantBinding.inflate(inflater)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val imageList: ArrayList<ChatModel> = arrayListOf()
//        imageList.add(ChatModel("Arham", 0))
//        imageList.add(ChatModel("Zaina", 0))
//        imageList.add(ChatModel("Uzair", 0))
//        imageList.add(ChatModel("Mariya", 0))
//        imageList.add(ChatModel("Ibad", 0))
//        imageList.add(ChatModel("Kinza", 0))
//        binding.recyMainScreen.adapter = ChatAdapter(imageList)
//    }
}