package com.example.privatehelper.adapters

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.databinding.ItemPurchaseFoodBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class FoodAdapterDelegate(
    private val onItemLongClick: (position: Int) -> Boolean,
    private val onLocationButtonClick: (hasLocation: Boolean) -> Unit,
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
        private val onLocationButtonClick: (hasLocation: Boolean) -> Unit,
        private val onRememberButtonClick: () -> Unit,
        private val onEditButtonClick: () -> Unit
    ) : PurchaseAdapter.BasePurchaseHolder(
        binding,
        onItemLongClick
    ) {

        @SuppressLint("ResourceAsColor")
        fun bind(food: PurchaseModel.Food) {

            val formatter = DateTimeFormatter
                .ofPattern("HH:mm dd/MM/yy").withZone(ZoneId.systemDefault())
            var hasLocation = false
            with(binding) {
                addLocationButton.setOnClickListener {

                    if (hasLocation) {
                        onLocationButtonClick(hasLocation)
                    } else {
                        onLocationButtonClick(hasLocation)
                        if (ActivityCompat.checkSelfPermission(
                                binding.root.context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            with(binding) {
                                addLocationButton.setImageResource(R.drawable.ic_location_on)
                                hasLocation = !hasLocation
                            }
                        }
                    }
                }
                editRememberButton.setOnClickListener { onRememberButtonClick() }
                editItemButton.setOnClickListener { onEditButtonClick() }
                dateTextView.text = formatter.format(food.createdAt)
                listOfPurchasesTextView.text = food.purchasesList

            }

        }
    }

}