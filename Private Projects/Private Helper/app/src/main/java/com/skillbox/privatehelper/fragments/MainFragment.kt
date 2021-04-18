package com.skillbox.privatehelper.fragments

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.privatehelper.R
import com.skillbox.privatehelper.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

}