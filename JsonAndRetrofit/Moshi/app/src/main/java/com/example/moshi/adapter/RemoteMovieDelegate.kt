package com.example.moshi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moshi.R
import com.example.moshi.RemoteMovie
import com.example.moshi.databinding.ItemMovieBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class RemoteMovieDelegate(
    private val onItemClicked: (position: Int) -> Unit,
) :
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
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClicked,
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
        private val binding: ItemMovieBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: RemoteMovie) {
            with(binding) {
                titleTextView.text = movie.title
                yearTextView.text = movie.year.toString()
                rateTextView.text = movie.rate.toString()
                genreTextView.text = movie.genre
                sourceScoreTextView.text = ""
                evaluationTextView.text = ""

                movie.scores.forEach {
                    val currentScore = sourceScoreTextView.text.toString()
                    val currentEvaluation = evaluationTextView.text.toString()
                    sourceScoreTextView.text = sourceScoreTextView.resources.getString(
                        R.string.sources,
                        currentScore,
                        "\n\n",
                        it.key
                    ).trim()
                    evaluationTextView.text = evaluationTextView.resources.getString(
                        R.string.evaluations,
                        currentEvaluation,
                        "\n\n",
                        it.value
                    ).trim()
                }
            }

            Glide.with(binding.root.context)
                .load(movie.posterUrl)
                .centerCrop()
                .into(binding.posterImageView)

            binding.addScoreButton.setOnClickListener {
                onItemClicked(adapterPosition)
            }


        }

    }

}