package com.example.privatehelper.extensions

import android.os.Parcelable
import com.example.privatehelper.PurchaseModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class StatePurchase(val purchases: @RawValue List<PurchaseModel>): Parcelable