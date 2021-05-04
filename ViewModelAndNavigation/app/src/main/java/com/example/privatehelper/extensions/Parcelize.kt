package com.example.privatehelper.extensions

import android.os.Parcelable
import android.text.SpannableString
import com.example.privatehelper.PurchaseModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PurchasesModelList(val list: @RawValue List<PurchaseModel>) : Parcelable

@Parcelize
data class UpdatedItem(
    val position: Int,
    val notCompletedPurchasesList: List<String>,
    val completedPurchasesList: @RawValue List<SpannableString>
) : Parcelable