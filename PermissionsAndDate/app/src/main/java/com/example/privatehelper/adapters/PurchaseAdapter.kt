package com.example.privatehelper.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.privatehelper.PurchaseModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PurchaseAdapter(
    binding: ViewBinding,
    onItemLongClick: (position: Int) -> Boolean,
    onLocationButtonClick: (hasLocation: Boolean) -> Unit,
    onRemindButtonClick: (hasRemember: Boolean, forEdit: Boolean) -> Unit,
    onEditButtonClick: () -> Unit,
    onAlarmButtonClick: () -> Unit
) : AsyncListDifferDelegationAdapter<PurchaseModel>(PurchaseDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(
            FoodAdapterDelegate(
                onItemLongClick,
                onLocationButtonClick,
                onRemindButtonClick,
                onEditButtonClick,
                onAlarmButtonClick
            )
        )
    }


    class PurchaseDiffUtilCallback : DiffUtil.ItemCallback<PurchaseModel>() {
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
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                onItemLongClick(adapterPosition)
            }
        }

    }
}