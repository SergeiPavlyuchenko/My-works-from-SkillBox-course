package com.example.fragments_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentMainBinding

open class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {

    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listFragmentLaunch()
    }


    override fun mainFragmentLaunch() = Unit

    override fun listFragmentLaunch() {
        childFragmentManager.beginTransaction()
            .replace(binding.mainContainerIntoMainFragment.id, ListFragment())
            .commit()
    }
}