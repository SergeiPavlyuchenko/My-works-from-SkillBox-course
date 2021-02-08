package com.example.fragments_1

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentListBinding
import com.example.fragments_1.databinding.FragmentMainBinding

class ListFragment: Fragment(R.layout.fragment_list)  {

    private val binding by viewBinding(FragmentListBinding::bind)

    private val fragment = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        DebugLogger.d("ListFragment", "fragment = $parentFragment")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DebugLogger.d("ListFragment", "onActivityCreated")
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? LinearLayout }
            .forEach { linearLayout ->
                linearLayout.setOnClickListener {
                    itemSelectListener?.onItemSelected()
                    DebugLogger.d("ListFragment", "after onItemSelected()|fragment = $parentFragment")
                }
            }
    }



}