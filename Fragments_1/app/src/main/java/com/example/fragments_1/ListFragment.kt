package com.example.fragments_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentListBinding
import com.example.fragments_1.databinding.FragmentMainBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)

    private val fragment: Fragment?
        get() = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }

    private var pressedTime: Long = 0

    val defaultLayout = binding.pineappleLayout.id

    val layouts: Map<Int, Int> = mapOf(
        defaultLayout to R.layout.fragment_pineapple
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    activity?.finish()
                } else {
                    Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
                }
                pressedTime = System.currentTimeMillis()
            }
        }
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DebugLogger.d("ListFragment", "onActivityCreated|${hashCode()}")
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? LinearLayout }
            .forEach { linearLayout ->
                linearLayout.setOnClickListener {
                    layoutId = it.id
                    itemSelectListener?.onItemSelected()
                }
            }
    }

    companion object {
        var layoutId: Int? = null


    }
}
