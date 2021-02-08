package com.example.intents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.intents.databinding.ActivityDeeplinkBinding


class DeeplinkActivity : AppCompatActivity(R.layout.activity_deeplink) {

    private val binding by viewBinding(ActivityDeeplinkBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentData()

        binding.callSecondTestActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondTestActivity::class.java))

        }
        Log.d("DeeplinkActivity", "onCreate|${hashCode()}")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("DeeplinkActivity", "onNewIntent|${hashCode()}")
    }

    //http://student_name.com/info
    private fun handleIntentData() {
        intent.data?.lastPathSegment?.let {
            binding.deeplinkTextView.text = it
        }
    }

}