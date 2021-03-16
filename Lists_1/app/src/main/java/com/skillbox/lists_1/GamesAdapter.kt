package com.skillbox.lists_1

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GamesAdapter(
//    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var games: List<GameGenre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            KEY_SHOOTER -> ShooterHolder(parent.inflate(R.layout.item_shooter), /*onItemClick*/)
            KEY_STRATEGY -> StrategyHolder(parent.inflate(R.layout.item_strategy), /*onItemClick*/)
            KEY_CCG -> CcgHolder(parent.inflate(R.layout.item_ccg), /*onItemClick*/)
            else -> error("Incorrect viewType = $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(games[position]) {
            is GameGenre.Shooters -> KEY_SHOOTER
            is GameGenre.Strategy -> KEY_STRATEGY
            is GameGenre.Ccg -> KEY_CCG
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
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
            else -> error("Incorrect viewHolder")
        }
    }

    override fun getItemCount(): Int = games.size

    fun updateGames(newGames: List<GameGenre>) {
        games = newGames
    }

    abstract class BaseGames(
        view: View,
//        onItemClick: (position: Int) -> Unit
    ): RecyclerView.ViewHolder(view)  {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val avatarLinkImageView: ImageView = view.findViewById(R.id.avatarImageView)
        private val rateTextView: TextView = view.findViewById(R.id.rateGameTextView)
        protected val isCoopCheckBox: CheckBox = view.findViewById(R.id.coopModeCheckBox)

        init {
//            view.setOnClickListener {
//                onItemClick(adapterPosition)
//            }
        }

        protected fun bindMainInfo(
            name: String,
            avatarLink: String,
            rate: Float
        ) {
            nameTextView.text = name
            rateTextView.text = rate.toString()

            Glide.with(itemView)
                .load(avatarLink)
                .into(avatarLinkImageView)
        }
    }

    class ShooterHolder(
        view: View,
//        onItemClick: (position: Int) -> Unit
    ): BaseGames(view, /*onItemClick*/) {
        private val isCoopImageView: ImageView = view.findViewById(R.id.coopModeImageView)

        init {
            isCoopImageView.isVisible = isCoopCheckBox.isChecked
        }

        fun bind(game: GameGenre.Shooters) {
            bindMainInfo(game.name, game.avatarLink, game.rate)
        }
    }

    class StrategyHolder(
        view: View,
//        onItemClick: (position: Int) -> Unit
    ): BaseGames(view, /*onItemClick*/) {

        private val isCoopImageView: ImageView = view.findViewById(R.id.coopModeImageView)

        fun bind(game: GameGenre.Strategy) {
            bindMainInfo(game.name, game.avatarLink, game.rate)
            isCoopImageView.isVisible = isCoopCheckBox.isChecked
        }
    }

    class CcgHolder(
        view: View,
//        onItemClick: (position: Int) -> Unit
    ): BaseGames(view, /*onItemClick*/) {
        fun bind(game: GameGenre.Ccg) {
            bindMainInfo(game.name, game.avatarLink, game.rate)
        }
    }

    companion object {
        private const val KEY_SHOOTER = 1
        private const val KEY_STRATEGY = 2
        private const val KEY_CCG = 3
    }

}