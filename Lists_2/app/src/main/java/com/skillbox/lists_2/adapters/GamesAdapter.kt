package com.skillbox.lists_2.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.lists_2.GameGenre
import com.skillbox.lists_2.R
import com.skillbox.lists_2.databinding.ItemKeepClearBinding

class GamesAdapter(
    private val onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<GameGenre>(GamesDiffUtilCallBack()) {

//    private val differ = AsyncListDiffer<GameGenre>(this, GamesDiffUtilCallBack())
//    private val delegatesManager = AdapterDelegatesManager<List<GameGenre>>()

    init {
        delegatesManager.addDelegate(ShootersAdapterDelegate(onItemClick))
            .addDelegate(StrategyAdapterDelegate(onItemClick))
            .addDelegate(CcgAdapterDelegate(onItemClick))
            .addDelegate(KeepClearAdapterDelegate(onItemClick))
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(differ.currentList, position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(differ.currentList, position, holder)
    }

    override fun getItemCount(): Int = differ.currentList.size*/

    /*fun updateGames(newGames: List<GameGenre>, context: Context) {
        val image: Drawable? = ContextCompat.getDrawable(context, R.drawable.keep_it_clear)
        val keepClear: GameGenre = GameGenre.KeepClear(image = image)

        if (newGames.isEmpty()) {
            differ.submitList(listOf(keepClear))
        } else {
            differ.submitList(newGames)
        }
    }*/

    class GamesDiffUtilCallBack : DiffUtil.ItemCallback<GameGenre>() {
        override fun areItemsTheSame(oldItem: GameGenre, newItem: GameGenre): Boolean {
            return when {
                oldItem is GameGenre.Ccg && newItem is GameGenre.Ccg -> oldItem.id == newItem.id
                oldItem is GameGenre.Ccg && newItem is GameGenre.Ccg -> oldItem.id == newItem.id
                oldItem is GameGenre.Ccg && newItem is GameGenre.Ccg -> oldItem.id == newItem.id
                oldItem is GameGenre.KeepClear && newItem is GameGenre.KeepClear -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: GameGenre, newItem: GameGenre): Boolean {
            return oldItem == newItem
        }
    }

    abstract class BaseGames(
        binding: ViewBinding,
        onItemClick: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }
}
