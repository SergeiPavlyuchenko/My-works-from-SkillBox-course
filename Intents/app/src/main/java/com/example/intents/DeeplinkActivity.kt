package com.example.intents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.databinding.ActivityDeeplinkBinding

private lateinit var binding: ActivityDeeplinkBinding
class DeeplinkActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNewIntent(Companion.getIntent(this))
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntentData()
    }

    //http://student_name.com/info
    private fun handleIntentData() {
        intent.data?.lastPathSegment?.let {
            binding.deeplinkTextView.text = it
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, DeeplinkActivity::class.java)
        }
    }

}