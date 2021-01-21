package com.example.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.viewandlayout.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Glide.with(this)
            .load("https://www.ut6.ru/files/site/top-header-bg.jpg")
//                не работает centerCrop аналогично scaleType="centerCrop" в разметке.
//                Не нашёл, как это можно ещё настроить(
            .centerCrop()
            .into(binding.labelImage)

        var emailDefined = false
        var passwordDefined = false
        val checkAcceptIsChecked = binding.checkAccept.isChecked

        binding.inputEmail.doOnTextChanged { text, _, _, _ ->
            emailDefined = text?.isNotBlank() ?: false
            logButtonValidate(checkAcceptIsChecked, emailDefined, passwordDefined)
        }

        binding.inputPass.doOnTextChanged { text, _, _, _ ->
            passwordDefined = text?.isNotBlank() ?: false
            logButtonValidate(checkAcceptIsChecked, emailDefined, passwordDefined)
        }

        binding.checkAccept.setOnCheckedChangeListener { _, isChecked ->
            logButtonValidate(isChecked, emailDefined, passwordDefined)
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
        firstParam: Boolean,
        secondParam: Boolean,
        thirdParam: Boolean
    ) {
        binding.logButton.isEnabled = firstParam && secondParam && thirdParam
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