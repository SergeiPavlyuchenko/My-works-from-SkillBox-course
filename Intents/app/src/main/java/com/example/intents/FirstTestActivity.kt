package com.example.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.intents.databinding.ActivityFirstTestBinding


//http://student_name.com/info/someText
class FirstTestActivity : AppCompatActivity(R.layout.activity_first_test) {
    private val binding by viewBinding(ActivityFirstTestBinding::bind)
    private val uri: Uri = "http://student_name.com/info/someText".toUri()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.handlerButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    uri
                )
            )
        }
    }
}