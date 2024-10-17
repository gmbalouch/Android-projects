package com.gmbaloch898.muetin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gmbaloch898.muetin.Models.User
import com.gmbaloch898.muetin.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor= Color.BLACK

        binding.loginBTN.setOnClickListener {

            if(binding.email.editText?.text.toString().equals("") or
                binding.password.editText?.text.toString().equals("")){
                    Toast.makeText(this@LoginActivity,
                        "Fill all fields",Toast.LENGTH_SHORT).show()
            }else{
                var user= User(binding.email.editText?.text.toString(),binding.password.editText?.text.toString())
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!).addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }
}