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
import com.skillbox.lists_2.databinding.ItemShooterBinding

class ShootersAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<GameGenre.Shooters, GameGenre, ShootersAdapterDelegate.ShooterHolder>() {

    override fun isForViewType(
        item: GameGenre,
        items: MutableList<GameGenre>,
        position: Int
    ): Boolean {
        return item is GameGenre.Shooters
    }

    override fun onCreateViewHolder(parent: ViewGroup): ShooterHolder {
        return ShooterHolder(
            ItemShooterBinding.inflate(LayoutInflater.from(parent.context)),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: GameGenre.Shooters,
        holder: ShooterHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ShooterHolder(
        private val binding: ItemShooterBinding,
        onItemClick: (position: Int) -> Unit
    ) : GamesAdapter.BaseGames(binding, onItemClick) {

        fun bind(game: GameGenre.Shooters) {
            with(binding) {
                nameTextView.text = game.name
                rateGameTextView.text = game.rate.toString()
                genreTextView.text = game.genre
                coopModeImageView.isVisible = game.isCoop
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