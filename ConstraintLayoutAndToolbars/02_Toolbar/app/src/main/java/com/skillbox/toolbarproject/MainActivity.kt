package com.skillbox.toolbarproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.skillbox.toolbarproject.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Glide.with(this)
                .load("https://photoplay.ru/sites/default/files/imce/2019/travel-photo_dk.jpg")
                .centerCrop()
                .into(binding.labelImage)

    }
}