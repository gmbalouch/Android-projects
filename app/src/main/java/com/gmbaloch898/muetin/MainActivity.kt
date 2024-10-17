package com.gmbaloch898.muetin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor= Color.BLACK

        Handler(Looper.getMainLooper()).postDelayed({
           startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        },4000)
    }

}