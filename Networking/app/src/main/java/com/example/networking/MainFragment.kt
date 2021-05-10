package com.example.networking

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.networking.databinding.FragmentMainBinding


class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.labels)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        (binding.moviesCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}