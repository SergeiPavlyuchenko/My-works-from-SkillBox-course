package com.example.fragments_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment

class ListFragment: Fragment(R.layout.fragment_list)  {

    private val fragment = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}