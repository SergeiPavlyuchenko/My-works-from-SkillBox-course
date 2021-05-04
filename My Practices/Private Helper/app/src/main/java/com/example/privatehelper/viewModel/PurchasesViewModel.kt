package com.example.privatehelper.viewModel

import androidx.lifecycle.ViewModel
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.databinding.FragmentPurchaseBinding

class PurchasesViewModel: ViewModel() {

    private val repository = PurchasesRepository()
    private var purchases: List<PurchaseModel> = emptyList()
    private var locations: Array<String> = emptyArray()
    private var locationInfo = ""



    fun addLocation(location: String) {
        purchases = repository.addLocation(purchases, location)
    }

    fun changeArrayLocations() {
        locations = arrayOf(locationInfo) + locations
    }

   fun addLocationInfo(newLocationInfo: String) {
       locationInfo = newLocationInfo
   }

    fun addPurchase(binding: FragmentPurchaseBinding) {
        purchases = repository.addPurchase(purchases, binding)
    }

    fun deletePurchase(position: Int) {
        purchases = repository.deletePurchase(purchases, position)
    }

    fun getPurchases() = purchases
    fun getLocations() = locations

}