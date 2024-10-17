package com.gmbaloch898.muetin.Post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.gmbaloch898.muetin.HomeActivity
import com.gmbaloch898.muetin.Models.Post
import com.gmbaloch898.muetin.R
import com.gmbaloch898.muetin.databinding.ActivityPostBinding
import com.gmbaloch898.muetin.utils.POST
import com.gmbaloch898.muetin.utils.POST_FOLDER
import com.gmbaloch898.muetin.utils.USER_PROFILE_FOLDER
import com.gmbaloch898.muetin.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class PostActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl:String?=null

    private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let{
            uploadImage(uri, POST_FOLDER) {
                url->
                if(url!=null){
                    binding.selectImage.setImageURI(uri)
                    imageUrl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }
        binding.selectImage.setOnClickListener{
            launcher.launch("image/*")
        }
        binding.cancelBTN.setOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
            finish()
        }

        binding.postBTN.setOnClickListener {
            val post: Post=Post(imageUrl!! , binding.captionTF.editText?.text.toString())

            Firebase.firestore.collection(POST).document().set(post).addOnCompleteListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnCompleteListener {
                    startActivity(Intent(this@PostActivity,HomeActivity::class.java))
                    finish()
                }
            }
        }
    }


}

