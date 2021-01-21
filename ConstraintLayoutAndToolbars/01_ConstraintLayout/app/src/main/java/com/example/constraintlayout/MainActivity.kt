package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged
import com.example.constraintlayout.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var emailDefined = false
        var passwordDefined = false
        val checkBoxIsChecked = binding.checkAccept.isChecked


        binding.inputEmail.doOnTextChanged { text, _, _, _ ->
            emailDefined = text?.isNotBlank() ?: false
            logButtonValidate(checkBoxIsChecked, emailDefined, passwordDefined)
        }

        binding.inputPass.doOnTextChanged { text, _, _, _ ->
            passwordDefined = text?.isNotBlank() ?: false
            logButtonValidate(checkBoxIsChecked, emailDefined, passwordDefined)
        }

        binding.checkAccept.setOnCheckedChangeListener { _, isChecked ->
            logButtonValidate(isChecked, emailDefined, passwordDefined)
        }


        binding.logButton.setOnClickListener {
            binding.inputEmail.isEnabled = false
            binding.inputPass.isEnabled = false
            binding.checkAccept.isEnabled = false
            it.isEnabled = false
            it.id = View.generateViewId()

            val set = ConstraintSet()
            val barToAdd = ProgressBar(this)
            barToAdd.id = View.generateViewId()
            binding.mainContainer.addView(barToAdd)
            set.clone(binding.mainContainer)
            set.connect(
                barToAdd.id,
                ConstraintSet.TOP,
                binding.logButton.id,
                ConstraintSet.BOTTOM,
                24
            )
            set.connect(
                barToAdd.id,
                ConstraintSet.LEFT,
                binding.logButton.id,
                ConstraintSet.LEFT,
            )
            set.connect(
                barToAdd.id,
                ConstraintSet.RIGHT,
                binding.logButton.id,
                ConstraintSet.RIGHT,
            )
            set.connect(
                binding.checkAccept.id,
                ConstraintSet.BOTTOM,
                binding.logButton.id,
                ConstraintSet.TOP,
                45
            )
            set.applyTo(binding.mainContainer)


            Handler().postDelayed({
                changeStates()
                barToAdd.visibility = View.GONE
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