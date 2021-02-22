package com.example.fragments_1


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.util.PatternsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val itemSelectListener: ItemSelectListener?
        get() = activity?.let { it as? ItemSelectListener }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
            logButtonImplementation()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_COUNTER, FormState(binding.errorTextView.isVisible, ""))
        outState.putParcelable(KEY_COUNTER, FormState(binding.logButton.isEnabled, ""))
    }


/*
       override fun onViewStateRestored(savedInstanceState: Bundle?) {
           super.onViewStateRestored(savedInstanceState)
           binding.errorTextView.isVisible =
               savedInstanceState?.getParcelable<FormState>(KEY_COUNTER)?.valid
                   ?: error("Unexpected state")
           binding.logButton.isEnabled =
               savedInstanceState.getParcelable<FormState>(KEY_COUNTER)?.valid
                   ?: error("Unexpected state")
       }
*/


    private fun logButtonImplementation() {
        binding.errorTextView.isVisible = false
        binding.inputEmail.isEnabled = false
        binding.inputPass.isEnabled = false
        binding.checkAccept.isEnabled = false
        binding.logButton.isEnabled = false


        binding.logButton.id = View.generateViewId()

        val set = ConstraintSet()
        val barToAdd = ProgressBar(context)
        barToAdd.id = View.generateViewId()
        binding.mainContainerIntoLoginFragment.addView(barToAdd)
        set.clone(binding.mainContainerIntoLoginFragment)
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
        set.applyTo(binding.mainContainerIntoLoginFragment)


        Handler().postDelayed({
            changeStates()
            barToAdd.visibility = View.GONE

            if (!PatternsCompat.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString())
                    .matches()
            ) {
                binding.errorTextView.isVisible = true
            } else {
                itemSelectListener?.onItemSelected()
            }
        }, 2000)

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



