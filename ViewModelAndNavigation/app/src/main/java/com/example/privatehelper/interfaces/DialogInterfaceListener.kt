package com.example.privatehelper.interfaces

import android.text.SpannableString

interface DialogInterfaceListener {
    fun onConfirm(
        position: Int,
        notCompletedPurchasesList: List<String>,
        completedPurchasesList: List<SpannableString>
    )
}