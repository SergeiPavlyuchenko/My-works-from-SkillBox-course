package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyCharacterMap
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.constraintlayout.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private val tag = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        12.12

        DebugLogger.d(tag, "onCreate")

        binding.armButton.setOnClickListener {
            Thread.sleep(10000)
        }

    }

    override fun onStart() {
        super.onStart()
        DebugLogger.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.d(tag, "onResume")



        binding.inputEmail.doOnTextChanged { _, _, _, _ ->
            logButtonValidate()
        }

        binding.inputPass.doOnTextChanged { _, _, _, _ ->
            logButtonValidate()
        }

        binding.checkAccept.setOnCheckedChangeListener { _, _ ->
            logButtonValidate()
        }

        binding.logButton.setOnClickListener {
            binding.errorTextView.isVisible = false
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
                if (!binding.inputEmail.text.contains("@")) {
                    binding.errorTextView.isVisible = true
                } else {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
            }, 2000)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            KEY_COUNTER, FormState(
                binding.errorTextView.isVisible,
                "State is ${binding.errorTextView.isVisible}"
            )
        )
        outState.putParcelable(KEY_COUNTER, FormState(binding.logButton.isEnabled, ""))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.errorTextView.isVisible =
            savedInstanceState.getParcelable<FormState>(KEY_COUNTER)?.valid
                ?: error("Unexpected state")
        binding.logButton.isEnabled =
            savedInstanceState.getParcelable<FormState>(KEY_COUNTER)?.valid
                ?: error("Unexpected state")
    }

    override fun onPause() {
        super.onPause()
        DebugLogger.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag, "onDestroy")
    }

    companion object {
        private const val KEY_COUNTER = "counter"
    }

}


object DebugLogger {
    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }

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
