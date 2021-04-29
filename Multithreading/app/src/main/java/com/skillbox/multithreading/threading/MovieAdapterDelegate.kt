package com.skillbox.multithreading.threading

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.multithreading.databinding.ItemMovieBinding
import com.skillbox.multithreading.networking.Movie

class MovieAdapterDelegate() :
    AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {


    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(item: Movie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class MovieHolder(
        private val binding: ItemMovieBinding
    ) : ThreadingAdapter.BaseHolder(binding) {

        fun bind(movie: Movie) {
            binding.titleTextView.text = movie.title
            binding.yearTextView.text = movie.year.toString()
        }

    }
}