package com.example.privatehelper.adapters

import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.privatehelper.PurchaseModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PurchaseAdapter(
    binding: ViewBinding,
    private val onItemLongClick: (position: Int) -> Boolean
) : AsyncListDifferDelegationAdapter<PurchaseModel>(PurchaseDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(FoodAdapterDelegate(onItemLongClick))
    }


    class PurchaseDiffUtilCallback: DiffUtil.ItemCallback<PurchaseModel>() {
        override fun areItemsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
            return when {
                oldItem is PurchaseModel.Food && newItem is PurchaseModel.Food -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: PurchaseModel, newItem: PurchaseModel): Boolean {
            return oldItem == newItem
        }

    }

    abstract class BasePurchaseHolder(
        binding: ViewBinding,
        onItemLongClick: (position: Int) -> Boolean
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                onItemLongClick(adapterPosition)
            }
        }

    }
}