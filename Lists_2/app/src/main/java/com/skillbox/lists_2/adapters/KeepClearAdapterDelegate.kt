package com.skillbox.lists_2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_2.GameGenre
import com.skillbox.lists_2.R
import com.skillbox.lists_2.databinding.ItemKeepClearBinding

class KeepClearAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<GameGenre.KeepClear, GameGenre, KeepClearAdapterDelegate.KeepClearHolder>() {

    override fun isForViewType(
        item: GameGenre,
        items: MutableList<GameGenre>,
        position: Int
    ): Boolean {
        return item is GameGenre.KeepClear
    }

    override fun onCreateViewHolder(parent: ViewGroup): KeepClearHolder {
        return KeepClearHolder(
            ItemKeepClearBinding.inflate(
                LayoutInflater.from(parent.context)
            ), onItemClick
        )
    }

    override fun onBindViewHolder(
        item: GameGenre.KeepClear,
        holder: KeepClearHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class KeepClearHolder(
        private val binding: ItemKeepClearBinding,
        onItemClick: (position: Int) -> Unit
    ) : GamesAdapter.BaseGames(binding, onItemClick) {


        fun bind(game: GameGenre.KeepClear) {
            with(binding) {
                nameTextView.text = game.name
                rateGameTextView.text = game.rate.toString()
                genreTextView.text = game.genre
            }


            Glide.with(itemView)
                .load(game.avatarLink)
                .centerCrop()
                .placeholder(R.drawable.ic_videogame)
                .error(R.drawable.ic_error)
                .into(binding.avatarImageView)
        }
    }
}