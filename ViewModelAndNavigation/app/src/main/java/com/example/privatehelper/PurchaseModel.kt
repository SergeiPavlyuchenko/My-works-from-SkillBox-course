package com.example.privatehelper

import android.text.SpannableString
import org.threeten.bp.Instant

sealed class PurchaseModel {

    data class Food(
        val id: Long,
        val createdAt: Instant,
        var notCompletedPurchasesList: List<String>,
        var completedPurchasesList: List<SpannableString>
        ): PurchaseModel()

}