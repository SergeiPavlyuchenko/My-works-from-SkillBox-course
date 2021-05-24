package com.skillbox.github.ui.repository_list

import android.content.Context
import android.os.Bundle
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentRepo = args.remoteRepo
        viewModel.isStarred(currentRepo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentRepo()
    }

    private fun setCurrentRepo() {
        with(binding) {
            nameRepoTv.text = resources.getString(R.string.name_repo_string, currentRepo.name)
            idRepoTv.text = resources.getString(R.string.id_repo_string, currentRepo.id.toString())
            urlRepoTv.text = resources.getString(R.string.url_repo_string ,currentRepo.owner.url)
            starredRepoTv.text = resources.getString(R.string.starred_repo_string, currentRepo.owner.starredUrl)
        }

        Glide.with(requireContext())
            .load(currentRepo.owner.avatarUrl)
            .centerCrop()
            .into(avatarRepoIv)
    }
}