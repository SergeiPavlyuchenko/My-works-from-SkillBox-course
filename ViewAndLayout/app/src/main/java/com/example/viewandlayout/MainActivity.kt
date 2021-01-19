package com.example.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Glide.with(this)
//           ссылка - https://www.ut6.ru/files/site/top-header-bg.jpg картинку не загрузила
//           Просьба дать комментарий, как было бы правильно
            .load(R.drawable.top)
            .into(binding.labelImage)

        var emailDefined = false
        var passwordDefined = false
        var acceptChecked = false

        binding.inputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailDefined = s?.isNotBlank() ?: false
                binding.logButton.isEnabled =
                    binding.checkAccept.isChecked && emailDefined && passwordDefined
            }

        })

        binding.inputPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordDefined = s?.isNotBlank() ?: false
                binding.logButton.isEnabled =
                    binding.checkAccept.isChecked && emailDefined && passwordDefined
            }

        })

        binding.checkAccept.setOnCheckedChangeListener { _, isChecked ->
            binding.logButton.isEnabled = isChecked && emailDefined && passwordDefined
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
            }, 2000)
        }


    }


    private fun changeStates() {
        val email = findViewById<EditText>(R.id.inputEmail)
        val pass = findViewById<EditText>(R.id.inputPass)
        val checkbox = findViewById<CheckBox>(R.id.checkAccept)
        val logButton = findViewById<Button>(R.id.logButton)
        val items = listOf(email, pass, checkbox, logButton)
        items.forEach {
            it.isEnabled = !it.isEnabled
        }
    }

}