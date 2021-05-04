package com.example.privatehelper.viewModel

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.SingleLiveEvent
import com.example.privatehelper.databinding.FragmentPurchaseBinding

class PurchasesViewModel : ViewModel() {

    private val repository = PurchasesRepository()

    private val purchasesLiveData = MutableLiveData<List<PurchaseModel>>()
    val purchases: LiveData<List<PurchaseModel>>
        get() = purchasesLiveData

    private val purchasesStringListLiveData = SingleLiveEvent<List<String>>()
    val purchasesStringList: LiveData<List<String>>
        get() = purchasesStringListLiveData

    private val purchasesSpannableListLiveData = SingleLiveEvent<List<SpannableString>>()
    val purchasesSpannableList: LiveData<List<SpannableString>>
        get() = purchasesSpannableListLiveData

    private val showToastLiveData = SingleLiveEvent<Map<String, Long>>()
    val showToast: LiveData<Map<String, Long>>
        get() = showToastLiveData

    private val itemPositionLiveData = SingleLiveEvent<Int>()
    val getItemPosition: LiveData<Int>
        get() = itemPositionLiveData


    fun addPurchase(binding: FragmentPurchaseBinding) {
        val newPurchase = repository.createFoodPurchase(binding)
        val updatedList =
            if (newPurchase is PurchaseModel.Food && newPurchase.notCompletedPurchasesList.isEmpty()) {
                showToastLiveData.postValue(mapOf(ADD_PURCHASE_KEY to 0))
                purchasesLiveData.value.orEmpty()
            } else listOf(newPurchase) + purchasesLiveData.value.orEmpty()
        purchasesLiveData.postValue(updatedList)
    }

    fun deletePurchase(position: Int) {
        val updatedList = repository.deletePurchase(purchasesLiveData.value.orEmpty(), position)
        val purchaseID = repository.getLastDeletedItemID()
        showToastLiveData.postValue(mapOf(DELETE_PURCHASE_KEY to purchaseID))
        purchasesLiveData.postValue(updatedList)
    }

    fun updatePurchases(
        purchases: List<PurchaseModel>,
        position: Int,
        notCompletedPurchasesList: List<String>,
        completedPurchasesList: List<SpannableString>
    ) {
        val updatedList = repository.modifyCompletedPurchase(
            purchases,
            position,
            notCompletedPurchasesList,
            completedPurchasesList
        )
        purchasesLiveData.postValue(updatedList)
    }

    fun itemClicked(position: Int) {
        val purchasesStringList =
            repository.createPurchasesStringList(purchasesLiveData.value.orEmpty(), position)

        val purchasesSpannableList =
            repository.createPurchasesSpannableList(purchasesLiveData.value.orEmpty(), position)
        purchasesStringListLiveData.postValue(purchasesStringList)
        purchasesSpannableListLiveData.postValue(purchasesSpannableList)
        itemPositionLiveData.postValue(position)
    }

    companion object {
        const val ADD_PURCHASE_KEY = "Add purchase key"
        const val DELETE_PURCHASE_KEY = "Delete purchase key"
    }
}