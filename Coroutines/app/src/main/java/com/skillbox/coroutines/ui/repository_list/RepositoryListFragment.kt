package com.skillbox.coroutines.ui.repository_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.coroutines.R
import com.skillbox.coroutines.databinding.FragmentRepositoryListBinding

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {

    private val binding by viewBinding(FragmentRepositoryListBinding::bind)
    private val viewModel: RepoViewModel by viewModels()
    private var repoAdapter: RepositoriesAdapter? = null
    private var currentRepoList: List<RemoteRepo>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getRepositories()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeStates()
    }

    private fun initList() {
        repoAdapter = RepositoriesAdapter { position ->
            currentRepoList?.get(position)?.let { showDetailInfo(it) }
        }
        with(binding.repoListRv) {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun showDetailInfo(repo: RemoteRepo) {
        findNavController()
            .navigate(
                RepositoryListFragmentDirections
                    .actionRepositoryListFragmentToDetailRepoFragment(repo)
            )
    }

    private fun observeStates() {
        viewModel.remoteRepoList.observe(viewLifecycleOwner) {
            repoAdapter?.items = it
            currentRepoList = it
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                repoListRv.isVisible = !it
                loadingProgressbar.isVisible = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        repoAdapter = null
    }

}