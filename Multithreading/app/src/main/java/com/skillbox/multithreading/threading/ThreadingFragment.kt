package com.skillbox.multithreading.threading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.R
import com.skillbox.multithreading.databinding.FragmentThreadingBinding
import com.skillbox.multithreading.networking.Movie

class ThreadingFragment : Fragment(R.layout.fragment_threading) {

   private val bi by viewBinding(FragmentThreadingBinding::bind)
   private var threadingAdapter: ThreadingAdapter? = null


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      initRvMovieList()
   }

   private fun initRvMovieList() {
      threadingAdapter = ThreadingAdapter()
      with(bi.rvMoviesList) {
         adapter = threadingAdapter
         layoutManager = LinearLayoutManager(requireContext())
         addItemDecoration(ItemOffsetDecoration(requireContext()))
         setHasFixedSize(true)
      }
      threadingAdapter?.items = listOf(Movie("Hello", 23), Movie("Hello1", 232))
   }

   override fun onDestroy() {
      super.onDestroy()
      threadingAdapter = null
   }

}