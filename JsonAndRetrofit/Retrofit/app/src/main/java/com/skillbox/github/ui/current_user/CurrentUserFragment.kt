package com.skillbox.github.ui.current_user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentCurrentUserBinding


class CurrentUserFragment: Fragment(R.layout.fragment_current_user) {

    private val binding by viewBinding(FragmentCurrentUserBinding::bind)
    private val viewModel: CurrentUserViewModel by viewModels()
    private lateinit var currentUser: RemoteUser

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getCurrentUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
//        val user = RemoteUser("123",33,"asdasd","12asdas","https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.qkD6ztlVKw7VLj2KyFnQ0wHaFj%26pid%3DApi&f=1")

    }

    private fun bind(user: RemoteUser) {
        with(binding) {
            loginTextView.text = resources.getString(R.string.Login_str, user.login)
            idTextView.text = resources.getString(R.string.ID_str, user.id.toString())
            locationTextView.text = resources.getString(R.string.Location_str, user.location)
            emailTextView.text = resources.getString(R.string.Email_str, user.email ?: "Unknown")

            Glide.with(root)
                .load(user.avatarUrl)
                .centerCrop()
                .into(posterImageView)
        }
    }


    private fun observeStates() {
        viewModel.remoteUser.observe(viewLifecycleOwner) {
            bind(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                loadingProgressBar.isVisible = it
                backMainInfoImageView.isVisible = !it
                emailTextView.isVisible = !it
                idTextView.isVisible = !it
                imageView.isVisible = !it
                imageView2.isVisible = !it
                imageView3.isVisible = !it
                idTextView.isVisible = !it
                locationTextView.isVisible = !it
                loginTextView.isVisible = !it
                posterImageView.isVisible = !it
            }
        }
    }

}