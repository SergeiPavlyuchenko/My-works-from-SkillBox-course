package com.example.privatehelper.interfaces

import com.example.privatehelper.PurchaseModel

interface UpdatePurchasesInterface {
    fun updatePurchase(purchase: PurchaseModel) = Unit
}