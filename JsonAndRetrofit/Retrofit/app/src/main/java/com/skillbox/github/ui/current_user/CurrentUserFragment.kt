package com.skillbox.github.ui.current_user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentCurrentUserBinding


class CurrentUserFragment: Fragment(R.layout.fragment_current_user) {

    private val binding by viewBinding(FragmentCurrentUserBinding::bind)
    private val viewModel: CurrentUserViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getCurrentUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
        binding.setNewLocationButton.setOnClickListener {
            viewModel.updateUser(binding.locationChangeEditText.text.toString())
        }
    }

    private fun setCurrentUser(user: RemoteUser) {
        with(binding) {
            loginTextView.text = resources.getString(R.string.Login_str, user.login)
            idTextView.text = resources.getString(R.string.ID_str, user.id.toString())
            locationTextView.text = resources.getString(R.string.Location_str, user.location)
            emailTextView.text = resources.getString(R.string.Email_str, user.email ?: "Unknown")

            Glide.with(root)
                .load(user.avatarUrl)
                .centerCrop()
                .into(avatarUserImageView)
        }
    }


    private fun observeStates() {
        viewModel.remoteUser.observe(viewLifecycleOwner) {
            setCurrentUser(it)
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
                avatarUserImageView.isVisible = !it
                locationChangeEditText.isVisible = !it
                setNewLocationButton.isVisible = !it
            }
        }
    }

}