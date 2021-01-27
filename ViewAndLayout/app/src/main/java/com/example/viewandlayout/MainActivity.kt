package com.example.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.viewandlayout.databinding.ActivityMainBinding
import java.util.*

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

        binding.inputEmail.doOnTextChanged { _, _, _, _ ->
            logButtonValidate()
        }

        binding.inputPass.addTextChangedListener()

        binding.inputPass.doOnTextChanged { _, _, _, _ ->
            logButtonValidate()
        }

        binding.checkAccept.setOnCheckedChangeListener { _, _ ->
            logButtonValidate()
        }

        binding.logButton.setOnClickListener {
            binding.inputEmail.isEnabled = false
            binding.inputPass.isEnabled = false
            binding.checkAccept.isEnabled = false
            it.isEnabled = false

            val barToAdd = ProgressBar(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }
            }

            binding.mainContainer.addView(barToAdd)
            Handler().postDelayed({
                changeStates()
                binding.mainContainer.removeView(barToAdd)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }, 2000)

        }


    }


    private fun logButtonValidate(
    ) {
        binding.logButton.isEnabled = binding.inputEmail.text.isNotEmpty() &&
                binding.inputPass.text.isNotEmpty() &&
                binding.checkAccept.isChecked
    }

    private fun changeStates() {
        listOf(
            binding.inputEmail,
            binding.inputPass,
            binding.checkAccept,
            binding.logButton
        ).forEach {
            it.isEnabled = !it.isEnabled
        }
    }

}