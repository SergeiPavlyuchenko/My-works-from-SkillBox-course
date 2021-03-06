package com.skillbox.multithreading.threading

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.R
import com.skillbox.multithreading.databinding.FragmentThreadingBinding
import com.skillbox.multithreading.networking.Movie


class ThreadingFragment : Fragment(R.layout.fragment_threading) {

   private val viewModel: ThreadingViewModel by viewModels() {
      ThreadingViewModel.ThreadingViewModelFactory(requireActivity().application)
   }
   private val binding by viewBinding(FragmentThreadingBinding::bind)
   private var threadingAdapter: ThreadingAdapter? = null



   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      initRvMovieList()
      binding.requestMoviesButton.setOnClickListener {
         viewModel.requestMovies()
         Handler().postDelayed(1000) {
            Toast.makeText(requireContext(), "Movies updated", Toast.LENGTH_SHORT).show()
         }
      }

      binding.swiperefresh.setOnRefreshListener {
         viewModel.requestMovies()
      }
   }

   private fun initRvMovieList() {
      observeViewModelState()
      threadingAdapter = ThreadingAdapter()
      with(binding.rvMoviesList) {
         adapter = threadingAdapter
         layoutManager = LinearLayoutManager(requireContext())
         addItemDecoration(ItemOffsetDecoration(requireContext()))
         setHasFixedSize(true)
      }
//      threadingAdapter?.items = listOf(Movie("Hello", 23), Movie("Hello1", 232))
   }


   private fun observeViewModelState() {
      viewModel.movies.observe(viewLifecycleOwner, Observer {
         threadingAdapter?.items = it
      })

      viewModel.isLoading.observe(viewLifecycleOwner, Observer {
         binding.swiperefresh.isRefreshing = it
      })
   }

   override fun onDestroy() {
      super.onDestroy()
      threadingAdapter = null
   }

}