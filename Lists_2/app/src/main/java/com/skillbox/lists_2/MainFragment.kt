package com.skillbox.lists_2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.defaultRecyclerViewButton.setOnClickListener {
            parentFragmentManager.transaction(
                R.id.containerHostFragment,
                ListFragment.newInstance(ListFragment.KEY_VERTICAL)
            )
        }

        binding.horizontalRecyclerViewButton.setOnClickListener {
            parentFragmentManager.transaction(
                R.id.containerHostFragment, ListFragment.newInstance(
                    ListFragment.KEY_HORIZONTAL
                )
            )
        }

        binding.gridRecyclerViewButton.setOnClickListener {
            parentFragmentManager.transaction(
                R.id.containerHostFragment, ListFragment.newInstance(
                    ListFragment.KEY_GRID
                )
            )
        }

        binding.staggeredRecyclerViewButton.setOnClickListener {
            parentFragmentManager.transaction(
                R.id.containerHostFragment, ListFragment.newInstance(
                    ListFragment.KEY_STAGGER_GRID
                )
            )
        }

        binding.paginationRecyclerViewButton.setOnClickListener {
            parentFragmentManager.transaction(
                R.id.containerHostFragment, ListFragment.newInstance(
                    ListFragment.KEY_PAGINATION
                )
            )
        }


    }

    companion object {

    }
}