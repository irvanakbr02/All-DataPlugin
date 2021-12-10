package com.example.lifecycle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btneks.setOnClickListener {
            startActivity(Intent(this, MainActivity2 :: class.java))
        }
        btnimp.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https:/www.github.com/Irvanakbr02"))
            startActivity(intent)
        }
    }
}