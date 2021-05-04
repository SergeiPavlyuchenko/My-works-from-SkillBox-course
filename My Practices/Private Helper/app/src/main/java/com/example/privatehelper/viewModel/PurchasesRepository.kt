package com.example.privatehelper.viewModel

import android.widget.Toast
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchasesRepository {


    fun addPurchase(purchases: List<PurchaseModel>, binding: FragmentPurchaseBinding): List<PurchaseModel> {
        val purchase = createFoodPurchase(binding)
        return if (purchase is PurchaseModel.Food && purchase.purchasesList.isEmpty()) {
            Toast.makeText(binding.root.context, "Заполните список покупок", Toast.LENGTH_SHORT).show()
            emptyList()
        } else listOf(purchase) + purchases
    }


    fun deletePurchase(purchases: List<PurchaseModel>, position: Int): List<PurchaseModel> {
        return purchases.filterIndexed { index, _ -> index != position }
    }

    fun addLocation(locations: List<PurchaseModel>,location: String): List<PurchaseModel> {
        val newLocation = createLocation(location)
        return listOf(newLocation) + locations
    }

    private fun createFoodPurchase(binding: FragmentPurchaseBinding): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            binding.inputPurchaseEditText.text.toString()
        )
    }

    fun createLocation(location: String): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            location
        )
    }



}