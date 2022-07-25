package com.example.tuitionclasses.student

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tuitionclasses.WelComeScreenActivity
import com.example.tuitionclasses.databinding.FragmentStudantBinding
import com.example.tuitionclasses.student.ui.main.ClassDetailsActivity
import com.example.tuitionclasses.user.intro.DashBoard
import com.example.tuitionclasses.user.intro.LoginActivity
import com.example.tuitionclasses.user.intro.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class studentFragment : Fragment() {

    lateinit var binding: FragmentStudantBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase
    private lateinit var storage:FirebaseStorage
    private lateinit var seletedImage : Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_studant, container, false)
        binding = FragmentStudantBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database= FirebaseDatabase.getInstance()
        storage=FirebaseStorage.getInstance()
        auth=FirebaseAuth.getInstance()

        binding.txtLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), WelComeScreenActivity::class.java)
            startActivity(intent)
        }


        binding.imgProfile.setOnClickListener {
            val intent =Intent()
                    intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,1)
        }
        binding.btnUploadImg.setOnClickListener {
            if (seletedImage == null){
                Toast.makeText(requireContext()," please select your image",Toast.LENGTH_LONG).show()
            }else
                upLoadData()
        }

    }

    private fun upLoadData() {
        val reference = storage.reference.child("Profile").child(Date().time.toString())
        reference.putFile(seletedImage).addOnCompleteListener {
            if (it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener { tast->
                    upLoadInfo(tast.toString())
                }
            }
        }
    }

    private fun upLoadInfo(ImageUrl: String) {
        val user = User(auth.uid,"","",1 )
        database.reference.child("users")
            .child(auth.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                Toast.makeText(requireContext(),"Data inserted",Toast.LENGTH_LONG).show()
                startActivity(Intent(requireContext(),ClassDetailsActivity::class.java))
            }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null){

            if (data.data != null){
                seletedImage =data.data!!

                binding.imgProfile.setImageURI(seletedImage)

            }

        }
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