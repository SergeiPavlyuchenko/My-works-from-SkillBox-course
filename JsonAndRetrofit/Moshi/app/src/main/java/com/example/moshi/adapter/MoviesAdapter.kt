package com.example.moshi.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moshi.RemoteMovie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MoviesAdapter(
    onItemClicked: () -> Unit,
    onItemChange: (f:() -> Map<String, Int>) -> Unit
): AsyncListDifferDelegationAdapter<RemoteMovie>(RemoteMoviesDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RemoteMovieDelegate(onItemClicked, onItemChange))
    }

   class RemoteMoviesDiffUtilCallback: DiffUtil.ItemCallback<RemoteMovie>() {
       override fun areItemsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
           return true
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