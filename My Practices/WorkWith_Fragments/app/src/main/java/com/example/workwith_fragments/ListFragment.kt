package com.example.workwith_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.workwith_fragments.databinding.FragmentListBinding

private var _binding: FragmentListBinding? = null

class ListFragment : Fragment() {

    private val binding get() = _binding!!



    private val itemSelectListener: ItemSelectListener?
        get() = (activity?.let { it as? ItemSelectListener })

    init {
        Log.d("ListFragment", "Init activity = $activity")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ListFragment", "onAttach activity = $activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    onButtonClick(button.text.toString())
                }
            }
    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onButtonClick(buttonText: String) {
//        Log.d("ListFragment", "onButtonClick = $buttonText")
//        (activity as InteractionActivity).
        itemSelectListener?.onItemSelected(buttonText)
    }
}