package com.skillbox.workwith_intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skillbox.workwith_intents.databinding.ActivityEmailBinding


private lateinit var binding: ActivityEmailBinding

class EmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEmailParamsFromIntent()

    }

    private fun setEmailParamsFromIntent() {
        val addresses = intent.getStringArrayExtra(Intent.EXTRA_EMAIL)
        val subject = intent.getStringExtra(Intent.EXTRA_SUBJECT)

        binding.addressTextView.text = addresses?.joinToString() ?: "Email is not set"
        binding.subjectTextView.text = subject ?: "Subject is not set"
    }
}