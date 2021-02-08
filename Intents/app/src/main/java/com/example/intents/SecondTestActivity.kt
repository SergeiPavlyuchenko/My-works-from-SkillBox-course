package com.example.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.intents.databinding.ActivityFirstTestBinding
import com.example.intents.databinding.ActivitySecondTestBinding


class SecondTestActivity: AppCompatActivity(R.layout.activity_second_test) {
     private val binding by viewBinding(ActivitySecondTestBinding::bind)
     private val uri: Uri = "http://student_name.com/info/someText".toUri()

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding.handlerButtonFromSecondActivity.setOnClickListener {
               startActivity(
                    Intent(
                         Intent.ACTION_VIEW,
                         uri
                    )
               )
          }
     }

}