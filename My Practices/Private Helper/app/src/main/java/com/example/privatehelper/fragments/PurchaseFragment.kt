package com.example.privatehelper.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
import com.google.android.gms.location.LocationServices
import org.threeten.bp.Instant
import kotlin.random.Random

class PurchaseFragment : Fragment(R.layout.fragment_purchase),
    AdapterCallBackInterface {

    private val binding by viewBinding(FragmentPurchaseBinding::bind)
    private var purchases: List<PurchaseModel> = emptyList()
    private var purchaseAdapter by AutoClearedValue<PurchaseAdapter>()
    private var confirmDeleteAlertDialog: AlertDialog? = null
    private var needRationaleDialog: AlertDialog? = null
    private var locationInfo = ""
    private var rememberPurchaseInstant: Instant? = null


    private val locationContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    showLocationInfo()
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.permission_loc_deny),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    showLocationRationaleDialog()
                }
            }
        }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PURCHASES, StatePurchase(purchases))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.getParcelable<StatePurchase>(KEY_PURCHASES)
            ?.let { purchases = it.purchases }

        initList()

        with(binding) {
            sendPurchaseButton.setOnClickListener {
                updatePurchase(createFoodPurchase())
            }
            setRememberButton.setOnClickListener {
                onRememberButtonClick()
            }
        }


    }

    private fun initList() {
        purchaseAdapter = PurchaseAdapter(
            binding,
            onEditButtonClick = ::onEditButtonClick,
            onLocationButtonClick = { hasLocation -> onLocationButtonClick(hasLocation) },
            onRememberButtonClick = ::onRememberButtonClick,
            onItemLongClick = { position -> onItemLongClick(position) }
        )
        with(binding.purchasesRecyclerView) {
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            adapter = purchaseAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        initTimePiker()
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

    private fun updatePurchase(purchase: PurchaseModel) {
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

    override fun onLocationButtonClick(hasLocation: Boolean) {
        showCurrentLocationWithPermissionCheck(hasLocation)
    }

    override fun onRememberButtonClick() {
    }

    override fun onEditButtonClick() {
        Toast.makeText(requireContext(), "onEditButtonClick()", Toast.LENGTH_SHORT).show()
    }


    private fun showCurrentLocationWithPermissionCheck(hasLocation: Boolean) {
        if (hasLocation) {
            AlertDialog.Builder(requireContext())
                .setTitle("Координаты: ")
                .setMessage(locationInfo)
                .setPositiveButton("OK") { d, _ -> d.dismiss() }
                .show()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showLocationRationaleDialog()
            } else {
                locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                showLocationInfo()
            }
        }

    }


    private fun showLocationInfo() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener {
                    it?.let {
                        locationInfo = """
                            Широта: ${it.latitude}
                            Долгота: ${it.longitude}
                            Высота: ${it.altitude}
                            Точность: ${it.accuracy}
                        """.trimIndent()
                    }

                }
                .addOnCanceledListener {
                    Toast.makeText(
                        requireContext(),
                        "Запрос локации был отменён",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "запрос локации завершился неудачно",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun showLocationRationaleDialog() {
        needRationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Необходимо одобрение разрешения для отображения информации по локации")
            .setPositiveButton("Принять") { _, _ ->
                locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            .setNegativeButton("Отменить", null)
            .show()
    }

    private fun initTimePiker() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        confirmDeleteAlertDialog?.dismiss()
        confirmDeleteAlertDialog = null
        needRationaleDialog?.dismiss()
        needRationaleDialog = null
    }

    companion object {
        private const val KEY_PURCHASES = "Key of the purchases"
        const val KEY_LOCATION_INFO = "Key of the location info"
    }
}