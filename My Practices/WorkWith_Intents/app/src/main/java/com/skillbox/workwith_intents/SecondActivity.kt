package com.skillbox.workwith_intents

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.skillbox.workwith_intents.databinding.ActivitySecondBinding

private lateinit var binding: ActivitySecondBinding

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LifeCycleActivity","SecondActivity|onCreate|${hashCode()}")

        binding.textView.text = intent.getStringExtra(KEY_MESSAGE)

    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycleActivity","SecondActivity|onStart|${hashCode()}")

    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleActivity","SecondActivity|onResume|${hashCode()}")

    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycleActivity","SecondActivity|onPause|${hashCode()}")

    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycleActivity","SecondActivity|onStop|${hashCode()}")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycleActivity","SecondActivity|onDestroy|${hashCode()}")

    }

    companion object {
        private const val KEY_MESSAGE = "message key"

        fun getIntent(context: Context, message: String?): Intent {
            return Intent(context, SecondActivity::class.java)
                .putExtra(KEY_MESSAGE, message)
        }
    }
}



