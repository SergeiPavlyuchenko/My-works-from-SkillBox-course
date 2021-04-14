package com.example.privatehelper.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.AutoClearedValue
import com.example.privatehelper.ItemOffsetDecoration
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.adapters.PurchaseAdapter
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import com.example.privatehelper.extensions.StatePurchase
import com.example.privatehelper.interfaces.AdapterCallBackInterface
import com.example.privatehelper.interfaces.LocationInterface
import com.example.privatehelper.interfaces.UpdatePurchasesInterface
import com.google.android.gms.location.LocationServices
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchaseFragment : Fragment(R.layout.fragment_purchase), UpdatePurchasesInterface,
    AdapterCallBackInterface, LocationInterface {

    private val binding by viewBinding(FragmentPurchaseBinding::bind)
    private var purchases: List<PurchaseModel> = emptyList()
    private var purchaseAdapter by AutoClearedValue<PurchaseAdapter>()
    private var confirmDeleteAlertDialog: AlertDialog? = null
    private var needRationaleDialog: AlertDialog? = null
    private var locationInfo = ""

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PURCHASES, StatePurchase(purchases))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.getParcelable<StatePurchase>(KEY_PURCHASES)
            ?.let { purchases = it.purchases }

        initList()

        binding.sendPurchaseButton.setOnClickListener {
            updatePurchase(createFoodPurchase())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        confirmDeleteAlertDialog?.dismiss()
        confirmDeleteAlertDialog = null
        needRationaleDialog?.dismiss()
        needRationaleDialog = null
    }

    private fun initList() {
        purchaseAdapter = PurchaseAdapter(
            binding,
            onEditButtonClick = onEditButtonClick(),
            onLocationButtonClick = onLocationButtonClick(),
            onRememberButtonClick = onRememberButtonClick(),
            onItemLongClick = { position -> onItemLongClick(position) }
        )
        with(binding.purchasesRecyclerView) {
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            adapter = purchaseAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deletePurchase(position: Int): Boolean {
        purchases = purchases.filterIndexed { index, _ -> index != position }
        purchaseAdapter.items = purchases
        return true
    }

    private fun createFoodPurchase(): PurchaseModel {
        return PurchaseModel.Food(
            Random.nextLong(),
            Instant.now(),
            binding.inputPurchaseEditText.text.toString()
        )
    }

    override fun updatePurchase(purchase: PurchaseModel) {
        if (purchase is PurchaseModel.Food && purchase.purchasesList.isEmpty()) {
            Toast.makeText(requireContext(), "Заполните список покупок", Toast.LENGTH_SHORT).show()
        } else purchases = listOf(purchase) + purchases
        purchaseAdapter.items = purchases
        binding.inputPurchaseEditText.text.clear()
        binding.purchasesRecyclerView.scrollToPosition(0)
    }

    override fun onItemLongClick(position: Int): Boolean {
        confirmDeleteAlertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Удалить данный список покупок?")
            .setPositiveButton("Да") { _: DialogInterface, _: Int -> deletePurchase(position) }
            .setNegativeButton("Нет") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .show()
        return true
    }

    override fun onLocationButtonClick() {
        showCurrentLocationWithPermissionCheck()
    }

    override fun onRememberButtonClick() {
    }

    override fun onEditButtonClick() {
    }


    override fun showCurrentLocationWithPermissionCheck() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isLocationPermissionGranted) {
            showLocationInfo()
        } else {
            ifNeedRationale()
        }
    }

    override fun ifNeedRationale() {
        val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (needRationale) {
            showLocationRationaleDialog()
        } else {
            requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            showLocationInfo()
        } else {
            ifNeedRationale()
        }
    }

    override fun showLocationInfo(){
        var location = ""
        val noLocation = "Локация отсутствует"
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener {
                    location = it?.let {
                        """
                            Широта: ${it.latitude}
                            Долгота: ${it.longitude}
                            Высота: ${it.altitude}
                            Точность: ${it.accuracy}
                        """.trimIndent()
                    } ?: noLocation

                }
                .addOnCanceledListener {
                }
                .addOnFailureListener {
                }
            if (location != noLocation) {
            }
        }

    }

    override fun showLocationRationaleDialog() {
        needRationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Необходимо одобрение разрешения для отображения информации по локации")
            .setPositiveButton("Принять") { _, _ -> requestLocationPermission() }
            .setNegativeButton("Отменить", null)
            .show()
    }

    override fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )

    }

    companion object {
        private const val KEY_PURCHASES = "Key of the purchases"
        private const val PERMISSION_REQUEST_CODE = 713
        const val KEY_LOCATION_INFO = "Key of the location info"
    }
}