package com.example.fragments_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentDetailBinding
import com.example.fragments_1.databinding.FragmentPineappleBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ListFragment.layouts.forEach {
            if (arguments?.getInt(KEY_DETAILFRAGMENT) == it.key) {
                return inflater.inflate(it.value, container, false)
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        private const val KEY_DETAILFRAGMENT = "key_detail_fragment"

        fun newInstance(layoutID: Int?): DetailFragment {
            return DetailFragment().withArguments {
                putInt(KEY_DETAILFRAGMENT, layoutID ?: ListFragment.defaultLayout!!)
            }
        }
    }


}