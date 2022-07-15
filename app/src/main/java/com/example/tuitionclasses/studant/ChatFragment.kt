package com.example.tuitionclasses.studant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuitionclasses.R
import com.example.tuitionclasses.databinding.FragmentChatBinding
import com.example.tuitionclasses.studant.ui.main.ChatAdapter
import com.example.tuitionclasses.studant.ui.main.ChatModel


class ChatFragment : Fragment() {

  lateinit var binding:FragmentChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat, container, false)
        binding=FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imageList: ArrayList<ChatModel> = arrayListOf()
        imageList.add(ChatModel("Arham",0))
        imageList.add(ChatModel("Zaina",0))
        imageList.add(ChatModel("Uzair",0))
        imageList.add(ChatModel("Mariya",0))
        imageList.add(ChatModel("Ibad",0))
        imageList.add(ChatModel("Kinza",0))
        binding.recyMainScreen.adapter=ChatAdapter(imageList)




    }


}