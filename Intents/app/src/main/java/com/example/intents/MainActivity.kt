package com.example.intents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.databinding.ActivityMainBinding
import java.security.AccessControlContext


private lateinit var binding: ActivityMainBinding
private val tag = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCallButton.setOnClickListener {

        }

    }

    companion object {
        const val KEY_MESSAGE = "Main activity key"

        fun getIntent(context: Context, message: String? = null): Intent {
            return Intent(context, MainActivity::class.java)
                .putExtra(KEY_MESSAGE,message ?: "")
        }
    }
}