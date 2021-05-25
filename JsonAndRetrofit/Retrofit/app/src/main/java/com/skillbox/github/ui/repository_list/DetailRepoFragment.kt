package com.skillbox.github.ui.repository_list

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
import com.skillbox.github.databinding.FragmentDetailRepoBinding
import kotlinx.android.synthetic.main.fragment_detail_repo.*

class DetailRepoFragment: Fragment(R.layout.fragment_detail_repo) {

    private val binding by viewBinding(FragmentDetailRepoBinding::bind)
    private val viewModel: DetailRepoViewModel by viewModels()
    private val args: DetailRepoFragmentArgs by navArgs()
    private lateinit var currentRepo: RemoteRepo

    override fun onAttach(context: Context) {
        super.onAttach(context)
        currentRepo = args.remoteRepo
        viewModel.isStarred(currentRepo)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()

        binding.emptyStarImageView.setOnClickListener {
            viewModel.toStarRepo(currentRepo)
        }

        binding.filledStarImageView.setOnClickListener {
            viewModel.unStarRepo(currentRepo)
        }
    }

    private fun observeStates() {
        viewModel.remoteRepo.observe(viewLifecycleOwner) {
            setCurrentRepo(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                nameRepoTv.isVisible = !it
                idRepoTv.isVisible = !it
                urlRepoTv.isVisible = !it
                infoContainer.isVisible = !it
                isStarredTv.isVisible = !it
                loadingProgressBar.isVisible = it
            }
        }
    }

    private fun setCurrentRepo(repo: RemoteRepo) {
        with(binding) {
            nameRepoTv.text = resources.getString(R.string.name_repo_string, repo.name)
            idRepoTv.text = resources.getString(R.string.id_repo_string, repo.id.toString())
            urlRepoTv.text = resources.getString(R.string.url_repo_string ,repo.owner.url)
            isStarredTv.text = resources.getString(R.string.starred_repo_string, repo.isStarred.toString())
            emptyStarImageView.isVisible = !repo.isStarred
            filledStarImageView.isVisible = repo.isStarred
        }

        Glide.with(requireContext())
            .load(repo.owner.avatarUrl)
            .centerCrop()
            .into(avatarRepoIv)

        currentRepo = repo
    }
}