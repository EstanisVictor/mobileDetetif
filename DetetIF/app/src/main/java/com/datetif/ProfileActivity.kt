package com.datetif

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.datetif.controller.UserController
import com.datetif.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var profileImage: CircleImageView
    lateinit var userController: UserController
    var nameUser: String = ""
    var ra: String = ""
    var turma: String = ""

    private lateinit var imageUri:Uri

    private val pickGalleryImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                imageUri = uri
                binding.profileImage.setImageURI(imageUri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userController = UserController(baseContext)

        profileImage = findViewById(R.id.profileImage)
//        registerEvents()
        ra = intent.getStringExtra("ra").toString()
        turma = intent.getStringExtra("turma").toString()

        reloadDataUser(turma)
    }

    fun reloadDataUser(turma: String){
        val user = userController.getUserByRa(ra)
        binding.nameTxt.text = user?.nome
        binding.raTxt.text = user?.RA
        binding.emailTxt.text = user?.email
        binding.classTxt.text = turma
    }

    fun registerEvents(){
        binding.changeProileImageTxt.setOnClickListener {
            openGallery()
        }

    }

    private fun openGallery() {
        val openGalleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        openGalleryIntent.type = "image/*"
        pickGalleryImage.launch(openGalleryIntent)
    }
}