package com.skillbox.lists_2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.databinding.FragmentHostBinding

class HostFragment: Fragment(R.layout.fragment_host) {

    private val binding by viewBinding(FragmentHostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*if (savedInstanceState == null) {
            childFragmentManager.transaction(R.id.containerHostFragment, MainFragment(), true)
        }*/

        binding.test1TransactionRecyclerViewButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerActivity, TestFragment(), "TestFragment")
                .addToBackStack(null)
                .commit()
        }

        binding.test2TransactionRecyclerViewButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerActivity, TestFragment(), "TestFragment")
                .addToBackStack(null)
                .commit()
        }
    }


    companion object {
        const val HOST_FRAGMENT_TAG = "Tag of HostFragment"
    }
}