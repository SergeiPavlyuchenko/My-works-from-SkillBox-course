package com.example.privatehelper.fragments

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.R
import com.example.privatehelper.databinding.FragmentPurchaseBinding

class PurchaseFragment: Fragment(R.layout.fragment_purchase) {

    private val binding by viewBinding(FragmentPurchaseBinding::bind)

}