package com.gmbaloch898.muetin.Post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.gmbaloch898.muetin.HomeActivity
import com.gmbaloch898.muetin.Models.Reel
import com.gmbaloch898.muetin.Models.User
import com.gmbaloch898.muetin.databinding.ActivityReelsBinding
import com.gmbaloch898.muetin.utils.REELS
import com.gmbaloch898.muetin.utils.REELS_FOLDER
import com.gmbaloch898.muetin.utils.USER_NODE
import com.gmbaloch898.muetin.utils.uploadVideo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class ReelsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }

    lateinit var progressDialog:ProgressDialog

    private lateinit var videoUrl:String
    private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let{
            uploadVideo(uri, REELS_FOLDER, progressDialog) {
                    url->
                if(url!=null){
                    videoUrl=url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        binding.selectReels.setOnClickListener{
            launcher.launch("video/*")
        }
        binding.cancelBTN.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }

        binding.postBTN.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User=it.toObject<User>()!!

                val reel: Reel = Reel(videoUrl!!, binding.captionTF.editText?.text.toString(), user.image!!)

                Firebase.firestore.collection(REELS).document().set(reel).addOnCompleteListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REELS).document().set(reel).addOnCompleteListener {
                        startActivity(Intent(this@ReelsActivity,HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }
}