package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.constraintlayout.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var emailDefined = false
        var passwordDefined = false

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

            val barToAdd = findViewById<ProgressBar>(R.id.progressBar)

//            Покажите, пожалуйста, как динамечески добавить прогресс бар,
//            как задаются констрейнты. А то без них он естественно в верхний левый угол убегает.

//            val barToAdd = ProgressBar(this).apply {
//                layoutParams = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//                ).apply {
//                    startToEnd = binding.logButton
//                }
//            }
//            binding.mainContainer.addView(barToAdd)
            barToAdd.visibility = View.VISIBLE
            Handler().postDelayed({
                changeStates()
                barToAdd.visibility = View.GONE
                //Почему-то перестал отображаться данный Тост
                Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
            }, 2000)

        }


    }


    private fun changeStates() {
        listOf<View>(
            findViewById<EditText>(R.id.inputEmail),
            findViewById<EditText>(R.id.inputPass),
            findViewById<CheckBox>(R.id.checkAccept),
            findViewById<Button>(R.id.logButton)
        ).forEach {
            it.isEnabled = !it.isEnabled
        }
    }
}