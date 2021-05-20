package com.skillbox.github.ui.current_user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.AccessToken
import com.skillbox.github.databinding.FragmentCurrentUserBinding
import com.skillbox.github.network.Network
import com.skillbox.github.ui.auth.AuthViewModel


class CurrentUserFragment: Fragment(R.layout.fragment_current_user) {

    private val binding by viewBinding(FragmentCurrentUserBinding::bind)
    private val viewModel: CurrentUserViewModel by viewModels()
    private lateinit var currentUser: RemoteUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchUser("SergeiPavlyuchenko")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
//        val user = RemoteUser("123",33,"asdasd","12asdas","https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.qkD6ztlVKw7VLj2KyFnQ0wHaFj%26pid%3DApi&f=1")
        bind(currentUser)
    }

    private fun bind(user: RemoteUser) {
        with(binding) {
            loginTextView.text = user.login
            idTextView.text = user.id.toString()
            locationTextView.text = user.location
            emailTextView.text = user.email

            Glide.with(root)
                .load(user.avatarUrl)
                .centerCrop()
                .into(posterImageView)
        }
    }


    private fun observeStates() {
        viewModel.remoteUser.observe(viewLifecycleOwner) {
            currentUser = it
        }
    }

}