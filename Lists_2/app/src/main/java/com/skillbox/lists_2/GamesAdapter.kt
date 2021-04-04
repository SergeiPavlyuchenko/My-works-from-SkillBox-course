package com.skillbox.lists_2

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.skillbox.lists_2.databinding.ItemCcgBinding
import com.skillbox.lists_2.databinding.ItemKeepClearBinding
import com.skillbox.lists_2.databinding.ItemShooterBinding
import com.skillbox.lists_2.databinding.ItemStrategyBinding

class GamesAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var games: List<GameGenre> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            KEY_SHOOTER -> ShooterHolder(ItemShooterBinding.inflate(LayoutInflater.from(parent.context)), onItemClick)//ShooterHolder(parent.inflate(R.layout.item_shooter), onItemClick)
            KEY_STRATEGY -> StrategyHolder(ItemStrategyBinding.inflate(LayoutInflater.from(parent.context)), onItemClick)
            KEY_CCG -> CcgHolder(ItemCcgBinding.inflate(LayoutInflater.from(parent.context)), onItemClick)
            KEY_KEEP_CLEAR -> KeepClearHolder(ItemKeepClearBinding.inflate(LayoutInflater.from(parent.context)), onItemClick)
            else -> error("Incorrect viewType = $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (games[position]) {
            is GameGenre.Shooters -> KEY_SHOOTER
            is GameGenre.Strategy -> KEY_STRATEGY
            is GameGenre.Ccg -> KEY_CCG
            is GameGenre.KeepClear -> KEY_KEEP_CLEAR
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShooterHolder -> {
                val gameShooter = games[position].let { it as? GameGenre.Shooters }
                    ?: error("Game at the position $position not a shooter")
                holder.bind(gameShooter)
            }
            is StrategyHolder -> {
                val gameStrategy = games[position].let { it as? GameGenre.Strategy }
                    ?: error("Game at the position $position not a strategy")
                holder.bind(gameStrategy)
            }
            is CcgHolder -> {
                val gameCcg = games[position].let { it as? GameGenre.Ccg }
                    ?: error("Game at the position $position not a ccg")
                holder.bind(gameCcg)
            }
            is KeepClearHolder -> {
                val keepClear = games[position].let { it as? GameGenre.KeepClear }
                    ?: error("Item at the position $position not a keepClear")
                holder.bind(keepClear)
            }
            else -> error("Incorrect viewHolder")
        }
    }

    override fun getItemCount(): Int = games.size

    fun updateGames(newGames: List<GameGenre>, context: Context) {
        val image: Drawable? = ContextCompat.getDrawable(context, R.drawable.keep_it_clear)
        val keepClear: GameGenre = GameGenre.KeepClear(image = image)

        if (newGames.isEmpty()) {
            games = listOf(keepClear)
        } else {
            val hasEmptyPlaceholderBefore =
                games.size == 1 && games[0] is GameGenre.KeepClear

            games = newGames
            if (hasEmptyPlaceholderBefore) {
                notifyDataSetChanged()
            }
        }
    }

    abstract class BaseGames(
        binding: ViewBinding,
        onItemClick: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root){


        /*private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val avatarLinkImageView: ImageView = view.findViewById(R.id.avatarImageView)
        private val rateTextView: TextView = view.findViewById(R.id.rateGameTextView)
        private val genreTextView: TextView = view.findViewById(R.id.genreTextView)*/

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        /*protected fun bindMainInfo(
            name: String,
            avatarLink: String,
            rate: Float,
            genre: String
        ) {
            nameTextView.text = name
            rateTextView.text = rate.toString()
            genreTextView.text = genre

                Glide.with(itemView)
                    .load(avatarLink)
                    .centerCrop()
                    .placeholder(R.drawable.ic_videogame)
                    .error(R.drawable.ic_error)
                    .into(avatarLinkImageView)
        }*/
    }

    class ShooterHolder(
        binding: ItemShooterBinding,
        onItemClick: (position: Int) -> Unit
    ) : BaseGames(binding, onItemClick) {
        private val nameTextView: TextView = binding.nameTextView
        private val avatarLinkImageView: ImageView = binding.avatarImageView
        private val rateTextView: TextView = binding.rateGameTextView
        private val genreTextView: TextView = binding.genreTextView
        private val isCoopImageView: ImageView = binding.coopModeImageView


        fun bind(game: GameGenre.Shooters) {
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

    class StrategyHolder(
        binding: ItemStrategyBinding,
        onItemClick: (position: Int) -> Unit
    ) : BaseGames(binding, onItemClick) {

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

    class CcgHolder(
        binding: ItemCcgBinding,
        onItemClick: (position: Int) -> Unit
    ) : BaseGames(binding, onItemClick) {
        private val nameTextView: TextView = binding.nameTextView
        private val avatarLinkImageView: ImageView = binding.avatarImageView
        private val rateTextView: TextView = binding.rateGameTextView
        private val genreTextView: TextView = binding.genreTextView

        fun bind(game: GameGenre.Ccg) {
            nameTextView.text = game.name
            rateTextView.text = game.rate.toString()
            genreTextView.text = game.genre

            Glide.with(itemView)
                .load(game.avatarLink)
                .centerCrop()
                .placeholder(R.drawable.ic_videogame)
                .error(R.drawable.ic_error)
                .into(avatarLinkImageView)
        }
    }

    class KeepClearHolder(
        binding: ItemKeepClearBinding,
        onItemClick: (position: Int) -> Unit
    ) : BaseGames(binding, onItemClick) {
        private val nameTextView: TextView = binding.nameTextView
        private val avatarLinkImageView: ImageView = binding.avatarImageView
        private val rateTextView: TextView = binding.rateGameTextView
        private val genreTextView: TextView = binding.genreTextView

        fun bind(game: GameGenre.KeepClear) {
            nameTextView.text = game.name
            rateTextView.text = game.rate.toString()
            genreTextView.text = game.genre

            Glide.with(itemView)
                .load(game.avatarLink)
                .centerCrop()
                .placeholder(R.drawable.ic_videogame)
                .error(R.drawable.ic_error)
                .into(avatarLinkImageView)
        }
    }

    companion object {
        private const val KEY_SHOOTER = 1
        private const val KEY_STRATEGY = 2
        private const val KEY_CCG = 3
        private const val KEY_KEEP_CLEAR = 4
    }

}
