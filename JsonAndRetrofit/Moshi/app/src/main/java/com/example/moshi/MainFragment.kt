package com.example.moshi

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moshi.adapter.MoviesAdapter
import com.example.moshi.databinding.FragmentMainBinding
import com.example.moshi.viewModel.MoviesViewModel


class MainFragment : Fragment(R.layout.fragment_main), DialogInterfaceListener {


    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MoviesViewModel by viewModels()
    private var moviesAdapter by AutoClearedValue<MoviesAdapter>()
    private var userRequest = UserRequestFromGUI("", null, "")
    private var currentMovieList: List<RemoteMovie> = emptyList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.labels)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        (binding.moviesCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)



        initList()

        with(binding) {
            searchButton.setOnClickListener { searchButtonImpl() }
            retryButton.setOnClickListener { retryButtonImpl() }
        }


    }


    private fun initList() {
        observeStates()
        moviesAdapter = MoviesAdapter { position -> addScore(position) }
        val offsetDec = ItemOffsetDecoration(requireContext())

        with(binding.moviesRv) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(offsetDec)
            setHasFixedSize(true)
        }
    }

    private fun addScore(position: Int) {
//        val action = MainFragmentDirections.actionMainFragmentToAddScoreDialog(position)
//        findNavController().navigate(action)
        AddScoreDialog.newInstance(position).show(childFragmentManager, "")
    }

    private fun observeStates() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            moviesAdapter.items = it
            currentMovieList = it
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                titleEditText.isEnabled = !it
                yearEditText.isEnabled = !it
                moviesCategory.isEnabled = !it
                searchButton.isVisible = !it
                loadingProgressBar.isVisible = it
            }
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            with(binding) {
                titleEditText.isVisible = !it.second
                yearEditText.isVisible = !it.second
                moviesCategory.isVisible = !it.second
                searchButton.isVisible = !it.second
                moviesRv.isVisible = !it.second
                loadingProgressBar.isVisible = !it.second
                errorTextView.isVisible = it.second
                retryButton.isVisible = it.second
                errorTextView.text = it.first?.message
            }
        }

        viewModel.notFound.observe(viewLifecycleOwner) {
            with(binding) {
                moviesRv.isVisible = !it
                confusedImageView.isVisible = it
                nothingFoundTextView.isVisible = it
                youSearchedForTextView.isVisible = it
                resultSearchTextView.isVisible = it
                resultSearchTextView.text = resources.getString(
                    R.string.result_not_found_search,
                    titleEditText.text.toString()
                )
            }
        }
    }

    private fun searchButtonImpl() {
        val type = if (binding.completeTextView.text
                ?.toString() == resources.getString(R.string.not_selected)
        ) null else binding.completeTextView.text?.toString()?.lowercase()
        userRequest = UserRequestFromGUI(
            binding.titleEditText.text.toString(),
            binding.yearEditText.text?.toString(),
            type
        )
        viewModel.search(userRequest)
    }

    private fun retryButtonImpl() {
        viewModel.search(userRequest)
    }

    override fun onItemChange(score: String, value: String, position: Int) {
        viewModel.updatePurchases(currentMovieList, score, value, position)
    }

}