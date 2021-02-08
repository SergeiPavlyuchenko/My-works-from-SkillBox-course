package com.example.fragments_1

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentDetailBinding

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val fragment = parentFragment
    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }


}