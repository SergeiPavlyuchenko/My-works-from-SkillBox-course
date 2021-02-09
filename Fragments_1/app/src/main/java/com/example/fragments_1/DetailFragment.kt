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
import com.bumptech.glide.Glide
import com.example.fragments_1.databinding.FragmentPineappleBinding

class DetailFragment : Fragment() {

    private val binding by viewBinding(FragmentPineappleBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ListFragment().layouts.forEach {
            return when (arguments?.getInt(KEY_DETAILFRAGMENT)) {
                it.key -> inflater.inflate(it.value, container, false)
                else -> super.onCreateView(inflater, container, savedInstanceState)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private val fragment: Fragment?
        get() = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }

    companion object {
        private const val KEY_DETAILFRAGMENT = "key_detail_fragment"

        fun newInstance(layoutID: Int?): DetailFragment {
            return DetailFragment().withArguments {
                putInt(KEY_DETAILFRAGMENT, layoutID ?: ListFragment().defaultLayout)
            }
        }
    }


}