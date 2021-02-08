package com.example.fragments_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentMainBinding

open class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {

    private val binding by viewBinding(FragmentMainBinding::bind)


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
            .add(binding.mainContainerIntoMainFragment.id, DetailFragment())
            .commit()
    }
}