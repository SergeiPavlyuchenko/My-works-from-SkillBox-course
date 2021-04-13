package com.example.privatehelper.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.AutoClearedValue
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.adapters.PurchaseAdapter
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import com.example.privatehelper.interfaces.UpdatePurchasesInterface
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchaseFragment: Fragment(R.layout.fragment_purchase), UpdatePurchasesInterface {

    private val binding by viewBinding(FragmentPurchaseBinding::bind)
    private lateinit var purchase: List<PurchaseModel>
    private val purchaseAdapter by AutoClearedValue<PurchaseAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendPurchaseButton.setOnClickListener {
            updatePurchase(createFoodPurchase())
        }
    }


    private fun createFoodPurchase(): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            binding.inputPurchaseEditText.text.toString()
        )
    }

    override fun updatePurchase(purchase: PurchaseModel) {
        this.purchase = listOf(purchase) + this.purchase
        purchaseAdapter.items = this.purchase
        binding.purchasesRecyclerView.scrollToPosition(0)
    }
}