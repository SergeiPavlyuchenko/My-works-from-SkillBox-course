package com.example.workwith_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workwith_fragments.databinding.FragmentInfoBinding


private var _binding: FragmentInfoBinding? = null

class InfoFragment : Fragment(R.layout.fragment_info) {

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        Под капотом - так:
//        val textView = requireView().findViewById<TextView>(binding.inputTextView.id)
//        textView.text = requireArguments().getString(KEY_TEXT)
        binding.inputTextView.text = requireArguments().getString(KEY_TEXT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val KEY_TEXT = "key_text"


        fun newInstance(text: String): InfoFragment {
            return InfoFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }

}