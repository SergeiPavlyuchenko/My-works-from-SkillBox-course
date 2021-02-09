package com.example.fragments_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentMainBinding

open class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {

    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                childFragmentManager.popBackStack("ListFragment", 0)
            }
        }
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,callback)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(binding.mainContainerIntoMainFragment.id, ListFragment())
            .addToBackStack("ListFragment")
            .commit()
    }


    override fun onItemSelected() {
        DebugLogger.d("MainFragment", "onItemSelected()")
        childFragmentManager.beginTransaction()
            .replace(binding.mainContainerIntoMainFragment.id, DetailFragment())
            .addToBackStack(null)
            .commit()
    }
}