package com.example.privatehelper.viewModel


import androidx.lifecycle.ViewModel
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import com.example.privatehelper.fragments.PurchaseFragment

class PurchasesViewModel: ViewModel() {

    private val repository = PurchasesRepository()
    private var purchases: List<PurchaseModel> = emptyList()


    fun addPurchase(binding: FragmentPurchaseBinding) {
        val newPurchase = repository.createFoodPurchase(binding)
        if (newPurchase is PurchaseModel.Food && newPurchase.purchasesList.isEmpty()) {
            PurchaseFragment().toast(binding.root.context.getString(R.string.enter_list_of_purchases))
        } else purchases = listOf(newPurchase) + purchases
    }

    fun deletePurchase(position: Int) {
        purchases = repository.deletePurchase(purchases, position)
    }

    fun getPurchases() = purchases
}