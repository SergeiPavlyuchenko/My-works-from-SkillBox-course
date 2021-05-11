package com.example.networking

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.networking.databinding.FragmentMainBinding
import com.example.networking.viewModel.MoviesViewModel


class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel:MoviesViewModel by viewModels()
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.labels)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        (binding.moviesCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.searchButton.setOnClickListener {
            viewModel.search(
                binding.titleEditText.toString(),
                binding.yearEditText.toString(),
                binding.moviesCategory.toString().lowercase()
            )
        }

    }
}