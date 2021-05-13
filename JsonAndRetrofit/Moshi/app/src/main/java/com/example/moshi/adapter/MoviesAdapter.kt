package com.example.moshi.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moshi.RemoteMovie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MoviesAdapter: AsyncListDifferDelegationAdapter<RemoteMovie>(RemoteMoviesDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RemoteMovieDelegate())
    }

   class RemoteMoviesDiffUtilCallback: DiffUtil.ItemCallback<RemoteMovie>() {
       override fun areItemsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
           return oldItem.imdbID == newItem.imdbID
       }

       override fun areContentsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
           return oldItem == newItem
       }
   }

    /*abstract class BaseHolder(
       private val binding: ViewBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun mainBind(movie: RemoteMovie) {

        }

    }*/

}