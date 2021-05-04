package com.example.privatehelper.adapters

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.databinding.ItemPurchaseFoodBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class FoodAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit,
    private val onItemLongClick: (position: Int) -> Boolean,
    private val onLocationButtonClick: (hasLocation: Boolean) -> Unit,
    private val onRemindButtonClick: (hasRemember: Boolean, forEdit: Boolean) -> Unit,
    private val onEditButtonClick: () -> Unit,
    private val onAlarmButtonClick: () -> Unit
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
            onItemClick,
            onItemLongClick,
            onLocationButtonClick,
            onRemindButtonClick,
            onEditButtonClick,
            onAlarmButtonClick
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
        onItemClick: (position: Int) -> Unit,
        onItemLongClick: (position: Int) -> Boolean,
        private val onLocationButtonClick: (hasLocation: Boolean) -> Unit,
        private val onRemindButtonClick: (hasRemind: Boolean, forEdit: Boolean) -> Unit,
        private val onEditButtonClick: () -> Unit,
        private val onAlarmButtonClick: () -> Unit
    ) : PurchaseAdapter.BasePurchaseHolder(
        binding,
        onItemClick,
        onItemLongClick
    ) {

        private var hasLocation = false
        private var forEdit = true
        private var hasRemind = false


        @SuppressLint("ResourceAsColor")
        fun bind(food: PurchaseModel.Food) {

            val formatter = DateTimeFormatter
                .ofPattern("HH:mm dd/MM/yy").withZone(ZoneId.systemDefault())

            val notCompletedPurchasesSpanBuilder = SpannableStringBuilder()
            val completedPurchasesStringBuilder: StringBuilder = StringBuilder()
            val indicesOfStrikeStrings = mutableListOf<Pair<Int, Int>>()
            var finalSpanBuilder = SpannableStringBuilder()

            food.notCompletedPurchasesList.forEachIndexed { index, str ->
                notCompletedPurchasesSpanBuilder.append(str)
                if (food.completedPurchasesList.isEmpty() && index == food.notCompletedPurchasesList.lastIndex) {
                    notCompletedPurchasesSpanBuilder.append(".")
                } else notCompletedPurchasesSpanBuilder.append(", ")
            }

            food.completedPurchasesList.forEachIndexed { index, str ->
                val startIndex = completedPurchasesStringBuilder.length
                completedPurchasesStringBuilder.append(str)
                val pair: Pair<Int, Int> = Pair(startIndex, completedPurchasesStringBuilder.length)
                indicesOfStrikeStrings.add(pair)
                if (index == food.completedPurchasesList.lastIndex) {
                    completedPurchasesStringBuilder.append(".")
                } else completedPurchasesStringBuilder.append(", ")
            }

            val completedPurchasesSpanBuilder = SpannableString(completedPurchasesStringBuilder)

            indicesOfStrikeStrings.forEach {
                completedPurchasesSpanBuilder.setSpan(
                    StrikethroughSpan(),
                    it.first,
                    it.second,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                completedPurchasesSpanBuilder.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.teal_700
                        )
                    ),
                    it.first,
                    it.second,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
            }

            finalSpanBuilder =
                notCompletedPurchasesSpanBuilder.append(completedPurchasesSpanBuilder)

            with(binding) {
                addLocationButton.setOnClickListener { locBtnImplementation() }
                editRemindButton.setOnClickListener { remBtnImplementation() }
                editItemButton.setOnClickListener { onEditButtonClick() }
                hasAlarmImageButton.setOnClickListener { onAlarmButtonClick() }
                dateTextView.text = formatter.format(food.createdAt)
                listOfPurchasesTextView.text = finalSpanBuilder

            }
        }

        private fun remBtnImplementation() {
            if (hasRemind) {
                onRemindButtonClick(hasRemind, forEdit)
            } else {
                onRemindButtonClick(hasRemind, forEdit)
                binding.editRemindButton.setImageResource(R.drawable.ic_time_filled)
                binding.editRemindButton.isSelected = true
                binding.hasAlarmImageButton.isVisible = true
                hasRemind = true
            }
        }

        private fun locBtnImplementation() {
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
    }


}