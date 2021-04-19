package com.example.privatehelper.viewModel

import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchasesRepository {

    fun createFoodPurchase(binding: FragmentPurchaseBinding): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            binding.inputPurchaseEditText.text.toString()
        )
    }

    fun deletePurchase(purchases: List<PurchaseModel>, position: Int): List<PurchaseModel> {
        return purchases.filterIndexed { index, _ -> index != position }
    }

}