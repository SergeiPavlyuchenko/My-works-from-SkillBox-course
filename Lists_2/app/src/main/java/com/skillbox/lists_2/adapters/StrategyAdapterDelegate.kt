package com.skillbox.lists_2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_2.GameGenre
import com.skillbox.lists_2.R
import com.skillbox.lists_2.databinding.ItemStrategyBinding

class StrategyAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<GameGenre.Strategy, GameGenre, StrategyAdapterDelegate.StrategyHolder>() {

    override fun isForViewType(
        item: GameGenre,
        items: MutableList<GameGenre>,
        position: Int
    ): Boolean {
        return item is GameGenre.Strategy
    }

    override fun onCreateViewHolder(parent: ViewGroup): StrategyHolder {
        return StrategyHolder(
            ItemStrategyBinding.inflate(LayoutInflater.from(parent.context)),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: GameGenre.Strategy,
        holder: StrategyHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class StrategyHolder(
        binding: ItemStrategyBinding,
        onItemClick: (position: Int) -> Unit
    ) : GamesAdapter.BaseGames(binding, onItemClick) {

        private val nameTextView: TextView = binding.nameTextView
        private val avatarLinkImageView: ImageView = binding.avatarImageView
        private val rateTextView: TextView = binding.rateGameTextView
        private val genreTextView: TextView = binding.genreTextView
        private val isCoopImageView: ImageView = binding.coopModeImageView

        fun bind(game: GameGenre.Strategy) {
            nameTextView.text = game.name
            rateTextView.text = game.rate.toString()
            genreTextView.text = game.genre
            isCoopImageView.isVisible = game.isCoop

            Glide.with(itemView)
                .load(game.avatarLink)
                .centerCrop()
                .placeholder(R.drawable.ic_videogame)
                .error(R.drawable.ic_error)
                .into(avatarLinkImageView)
        }
    }
}