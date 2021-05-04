package com.example.privatehelper.viewModel

import android.text.SpannableString
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchasesRepository {
    private var lastDeletedItemID = 0L

    fun deletePurchase(purchases: List<PurchaseModel>, position: Int): List<PurchaseModel> {
        lastDeletedItemID = (purchases[position] as PurchaseModel.Food).id
        return purchases.filterIndexed { index, _ -> index != position }
    }

    fun modifyCompletedPurchase(
        purchases: List<PurchaseModel>,
        position: Int,
        notCompletedPurchasesList: List<String>,
        completedPurchasesList: List<SpannableString>
    ): List<PurchaseModel> {
        val currentPurchase = purchases[position] as PurchaseModel.Food
        val updatedPurchase = PurchaseModel.Food(
            Random.nextLong(),
            currentPurchase.createdAt,
            notCompletedPurchasesList,
            completedPurchasesList
        )
        return purchases.toMutableList().apply {
            this[position] = updatedPurchase
        }
    }

    fun createFoodPurchase(binding: FragmentPurchaseBinding): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            binding.inputPurchaseEditText.text.split(","),
            emptyList()
        )
    }

    fun createPurchasesStringList(purchases: List<PurchaseModel>, position: Int): List<String> {
        val purchasesStringList: MutableList<String> = mutableListOf()
        (purchases[position] as PurchaseModel.Food).notCompletedPurchasesList.forEach {
            purchasesStringList.add(it)
        }
        return purchasesStringList
    }

    fun createPurchasesSpannableList(purchases: List<PurchaseModel>, position: Int): List<SpannableString> {
        val purchasesSpannableList: MutableList<SpannableString> = mutableListOf()
        (purchases[position] as PurchaseModel.Food).completedPurchasesList.forEach {
            purchasesSpannableList.add(it)
        }
        return purchasesSpannableList
    }


    fun getLastDeletedItemID() = lastDeletedItemID

}