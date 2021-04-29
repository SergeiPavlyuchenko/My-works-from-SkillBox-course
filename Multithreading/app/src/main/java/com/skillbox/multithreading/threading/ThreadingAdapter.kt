package com.skillbox.multithreading.threading

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.multithreading.networking.Movie

class ThreadingAdapter() : AsyncListDifferDelegationAdapter<Movie>(MoviesDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class MoviesDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    abstract class BaseHolder(
         binding: ViewBinding
    ): RecyclerView.ViewHolder(binding.root) {



    }

}