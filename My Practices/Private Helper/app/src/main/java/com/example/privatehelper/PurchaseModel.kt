package com.example.privatehelper

import org.threeten.bp.Instant

sealed class PurchaseModel {

    data class Food(
        val id: Long,
        val createdAt: Instant,
        val purchasesList: String,
    ): PurchaseModel()

}