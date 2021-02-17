package com.example.workwith_viewpager

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.workwith_viewpager.databinding.FragmentOnboardingBinding

class OnboardingFragment: Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBinding (FragmentOnboardingBinding::bind)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))
        binding.textView.setText(requireArguments().getInt(KEY_TEXT))
        binding.imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))

    }


    companion object {

        private const val KEY_TEXT = "text"
        private const val KEY_COLOR = "color"
        private const val KEY_IMAGE = "image"

        fun newInstance(
            @StringRes textRes: Int,
            @ColorRes bgColorRes: Int,
            @DrawableRes drawableRes: Int
        ): OnboardingFragment {
            return OnboardingFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_COLOR, bgColorRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }
    }
}