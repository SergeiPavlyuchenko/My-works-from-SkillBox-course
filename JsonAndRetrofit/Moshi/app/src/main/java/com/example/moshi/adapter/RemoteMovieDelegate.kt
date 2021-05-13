package com.example.moshi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moshi.RemoteMovie
import com.example.moshi.databinding.ItemMovieBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class RemoteMovieDelegate :
    AbsListItemAdapterDelegate<RemoteMovie, RemoteMovie, RemoteMovieDelegate.MovieHolder>() {

    override fun isForViewType(
        item: RemoteMovie,
        items: MutableList<RemoteMovie>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        item: RemoteMovie,
        holder: MovieHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class MovieHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: RemoteMovie) {
            binding.titleTextView.text = movie.title
            binding.yearTextView.text = movie.year.toString()
            binding.rateTextView.text = movie.rate
            binding.genreTextView.text = movie.genre

            Glide.with(binding.root.context)
                .load(movie.posterUrl)
                .centerCrop()
                .into(binding.posterImageView)
        }

    }

}