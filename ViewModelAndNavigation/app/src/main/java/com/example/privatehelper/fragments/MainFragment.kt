package com.example.privatehelper.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.R
import com.example.privatehelper.databinding.FragmentMainBinding
import com.example.privatehelper.extensions.transaction

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.purchaseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_purchaseFragment)
        }
    }

}