package com.skillbox.github.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentMainBinding
import com.skillbox.github.ui.current_user.RemoteUser

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.currentUserInfoButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCurrentUserFragment())
        }

        binding.repositoryListButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToRepositoryListFragment())
        }
    }

}