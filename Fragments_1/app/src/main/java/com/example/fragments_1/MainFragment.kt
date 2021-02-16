package com.example.fragments_1

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.FragmentMainBinding


open class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener {

    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                childFragmentManager.popBackStack(getString(R.string.text_list_fragment), 0)
            }
        }
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!requireContext().isTablet()) {
            childFragmentManager.beginTransaction()
                .replace(binding.mainFragment.id, ListFragment())
                .addToBackStack(getString(R.string.text_list_fragment))
                .commit()
        }

    }


    override fun onItemSelected() {
        DebugLogger.d("MainFragment", "onItemSelected()")
        childFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(
                binding.mainFragment.id,
                DetailFragment.newInstance(ListFragment.layoutId)
            )
            .addToBackStack(null)
            .commit()
    }
}