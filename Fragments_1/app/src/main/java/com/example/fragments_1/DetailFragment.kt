package com.example.fragments_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentDetailBinding

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val fragment: Fragment?
        get() = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DebugLogger.d("ListFragment", "Detail|onActivityCreated|${hashCode()}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ListFragment", "Detail|onAttach|${hashCode()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ListFragment", "Detail|onCreate|${hashCode()}")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ListFragment", "Detail|onCreateView|${hashCode()}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("ListFragment", "Detail|onStart|${hashCode()}")

    }

    override fun onResume() {
        super.onResume()
        Log.d("ListFragment", "Detail|onResume|${hashCode()}")

    }

    override fun onPause() {
        super.onPause()
        Log.d("ListFragment", "Detail|onPause|${hashCode()}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ListFragment", "Detail|onDestroyView|${hashCode()}")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ListFragment", "Detail|onDestroy|${hashCode()}")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ListFragment", "Detail|onDetach|${hashCode()}")

    }


}