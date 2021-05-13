package com.example.moshi

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moshi.adapter.MoviesAdapter
import com.example.moshi.databinding.FragmentMainBinding
import com.example.moshi.viewModel.MoviesViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MoviesViewModel by viewModels()
    private var moviesAdapter by AutoClearedValue<MoviesAdapter>()
    private var userRequest = UserRequestFromGUI("", null, "")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.labels)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        (binding.moviesCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        initList()

        binding.searchButton.setOnClickListener { searchButtonImpl() }

        binding.retryButton.setOnClickListener { retryButtonImpl() }
    }


    private fun initList() {
        observeStates()
        moviesAdapter = MoviesAdapter()
        val offsetDec = ItemOffsetDecoration(requireContext())

        with(binding.moviesRv) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(offsetDec)
            setHasFixedSize(true)
        }
    }

    private fun observeStates() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            moviesAdapter.items = it
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
//        Handler(Looper.getMainLooper()).postDelayed(500) {
//            if (!hasError) {
//                with(binding) {
//                    titleEditText.isVisible = true
//                    yearEditText.isVisible = true
//                    moviesCategory.isVisible = true
//                    searchButton.isVisible = true
//                    moviesRv.isVisible = true
//                    errorTextView.isVisible = false
//                    retryButton.isVisible = false
//                }
//            }
//        }
    }
}