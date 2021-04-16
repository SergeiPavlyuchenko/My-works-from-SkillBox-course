package com.example.privatehelper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.databinding.ItemPurchaseFoodBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class FoodAdapterDelegate(
    private val onItemLongClick: (position: Int) -> Boolean,
    private val onLocationButtonClick: () -> Unit,
    private val onRememberButtonClick: () -> Unit,
    private val onEditButtonClick: () -> Unit
) :
    AbsListItemAdapterDelegate<PurchaseModel.Food, PurchaseModel, FoodAdapterDelegate.FoodHolder>() {

    override fun isForViewType(
        item: PurchaseModel,
        items: MutableList<PurchaseModel>,
        position: Int
    ): Boolean {
        return item is PurchaseModel.Food
    }

    override fun onCreateViewHolder(parent: ViewGroup): FoodHolder {
        return FoodHolder(
            ItemPurchaseFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemLongClick,
            onLocationButtonClick,
            onRememberButtonClick,
            onEditButtonClick
        )
    }

    override fun onBindViewHolder(
        item: PurchaseModel.Food,
        holder: FoodHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class FoodHolder(
        private val binding: ItemPurchaseFoodBinding,
        onItemLongClick: (position: Int) -> Boolean,
        private val onLocationButtonClick: () -> Unit,
        private val onRememberButtonClick: () -> Unit,
        private val onEditButtonClick: () -> Unit
    ) : PurchaseAdapter.BasePurchaseHolder(
        binding,
        onItemLongClick
    ) {

        fun bind(food: PurchaseModel.Food) {

            val formatter = DateTimeFormatter
                .ofPattern("HH:mm dd/MM/yy").withZone(ZoneId.systemDefault())

            with(binding) {
                addLocationButton.setOnClickListener { onLocationButtonClick }
                editRememberButton.setOnClickListener { onRememberButtonClick }
                editItemButton.setOnClickListener { onEditButtonClick }
                dateTextView.text = formatter.format(food.createdAt)
                listOfPurchasesTextView.text = food.purchasesList

            }

        }
    }

}