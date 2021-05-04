package com.example.privatehelper.fragments

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.AutoClearedValue
import com.example.privatehelper.ItemOffsetDecoration
import com.example.privatehelper.PurchaseModel
import com.example.privatehelper.R
import com.example.privatehelper.adapters.PurchaseAdapter
import com.example.privatehelper.databinding.FragmentPurchaseBinding
import com.example.privatehelper.databinding.ItemPurchaseFoodBinding
import com.example.privatehelper.extensions.StatePurchase
import com.example.privatehelper.interfaces.AdapterCallBackInterface
import com.example.privatehelper.viewModel.PurchasesViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import kotlin.random.Random

class PurchaseFragment : Fragment(R.layout.fragment_purchase),
    AdapterCallBackInterface {

    private val binding by viewBinding(FragmentPurchaseBinding::bind)
    private var purchaseAdapter by AutoClearedValue<PurchaseAdapter>()
    private var confirmDeleteAlertDialog: AlertDialog? = null
    private var needRationaleDialog: AlertDialog? = null
    private var showRemindDateDialog: AlertDialog? = null
    private var showLocationDialog: AlertDialog? = null
    private var remindPurchaseInstant: Instant? = null
    private lateinit var locationCallback: LocationCallback
    private var locationUpdatesMode: Boolean = false
    private var apiDisabledDialog: AlertDialog? = null
    private val purchasesViewModel: PurchasesViewModel by viewModels()


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0.locations.forEach {
                    var locationParam = ""
                    it?.let {
                        locationParam = """
                            Широта: ${it.latitude}
                            Долгота: ${it.longitude}
                            Высота: ${it.altitude}
                            Скорость: ${ it.speed }
                            Точность: ${it.accuracy}
                        """.trimIndent()
                    }
                    purchasesViewModel.addLocation(locationParam)
                    updatePurchases()
                    binding.purchasesRecyclerView.scrollToPosition(0)
                }
            }
        }
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        with(binding) {
            sendPurchaseButton.setOnClickListener {
                addPurchase()
                resetStateOfBottomUI()
            }
            setRemindButton.setOnClickListener {
                onRemindButtonClick(remindPurchaseInstant != null)
            }
            updatesLocationModeOnImageButton.setOnClickListener {
                locationUpdatesMode = !locationUpdatesMode
                checkPermissionAndImplement(::googlePlayServicesAvailable)
            }
        }
    }

    private fun checkPermissionAndImplement(f: () -> Unit) {
        val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (needRationale) {
            showLocationRationaleDialog()
        } else {
            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            f()
        }
    }

    private fun googlePlayServicesAvailable() {
        when (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext())) {
            ConnectionResult.SUCCESS -> {
                binding.updatesLocationModeOnImageButton.isSelected = locationUpdatesMode
                locationUpdates(locationUpdatesMode)
            }
            ConnectionResult.API_DISABLED -> {
                apiDisabledDialog = AlertDialog.Builder(requireContext())
                    .setMessage("Google Play Services отключены. Функционал ограничен")
                    .setCancelable(false)
                    .show()
            }
            ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> TODO()
        }
    }

    private fun locationUpdates(on: Boolean) {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        if (on) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                LocationServices.getFusedLocationProviderClient(requireContext())
                    .requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    )
            }
        } else {
            LocationServices.getFusedLocationProviderClient(requireContext())
                .removeLocationUpdates(locationCallback)
        }


    }

    private fun initList() {
        purchaseAdapter = PurchaseAdapter(
            binding,
            onEditButtonClick = ::onEditButtonClick,
            onLocationButtonClick = { hasLocation -> onLocationButtonClick(hasLocation) },
            onRemindButtonClick = ::onRemindButtonClick,
            onItemLongClick = { position -> onItemLongClick(position) },
            onAlarmButtonClick = ::onAlarmButtonClick
        )
        with(binding.purchasesRecyclerView) {
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            adapter = purchaseAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        updatePurchases()
    }

    private fun addPurchase() {
        purchasesViewModel.addPurchase(binding)
        updatePurchases()
        val itemBinding: ItemPurchaseFoodBinding = ItemPurchaseFoodBinding.inflate(LayoutInflater.from(requireContext()))
        itemBinding.editRemindButton.isSelected = true
        itemBinding.hasAlarmImageButton.isVisible = true
    }

    private fun deletePurchase(position: Int): Boolean {
        purchasesViewModel.deletePurchase(position)
        updatePurchases()
        return true
    }

    private fun updatePurchases() {
        purchaseAdapter.items = purchasesViewModel.getPurchases()
    }

    private fun resetStateOfBottomUI() {
        with(binding) {
            remindTextView.isVisible = false
            setRemindButton.isSelected = false
            setRemindButton.setImageResource(R.drawable.ic_more_time)
//            setRemindButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_700))
            inputPurchaseEditText.text.clear()
            purchasesRecyclerView.scrollToPosition(0)
        }

    }

    override fun onItemLongClick(position: Int): Boolean {
        confirmDeleteAlertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.question_delete))
            .setPositiveButton("Да") { _: DialogInterface, _: Int -> deletePurchase(position) }
            .setNegativeButton("Нет") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .show()
        return true
    }

    override fun onLocationButtonClick(hasLocation: Boolean) {
        showCurrentLocationWithPermissionCheck(hasLocation)
    }

    override fun onRemindButtonClick(hasRemind: Boolean, forEdit: Boolean) {
        val currentDateTime = LocalDateTime.now()
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        if (forEdit) {
                            editRememberTime(year, month, dayOfMonth, hourOfDay, minute)
                        } else {
                            setRememberTime(year, month, dayOfMonth, hourOfDay, minute)
                        }
                    },
                    currentDateTime.hour,
                    currentDateTime.minute,
                    true
                ).show()
            },
            currentDateTime.year,
            currentDateTime.month.value - 1,
            currentDateTime.dayOfMonth
        ).show()
    }

    override fun onEditButtonClick() {
        Toast.makeText(requireContext(), "onEditButtonClick()", Toast.LENGTH_SHORT).show()
    }


    private fun setRememberTime(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hourOfDay: Int,
        minute: Int
    ) {
        val zoneDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
            .atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter
            .ofPattern("Установленое время напоминания: HH:mm dd/MM/yy")
            .withZone(ZoneId.systemDefault())
        Toast.makeText(requireContext(), "Выбрано время: $zoneDateTime", Toast.LENGTH_SHORT).show()
        remindPurchaseInstant = zoneDateTime.toInstant()
        with(binding.setRemindButton) {
            setImageResource(R.drawable.ic_time_filled)
            isSelected = true
        }
        binding.remindTextView.text = formatter.format(zoneDateTime)
        binding.remindTextView.isVisible = true
    }

    private fun editRememberTime(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hourOfDay: Int,
        minute: Int
    ) {
        val zoneDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
            .atZone(ZoneId.systemDefault())
        Toast.makeText(
            requireContext(),
            "Время напомминания изменено на: $zoneDateTime",
            Toast.LENGTH_SHORT
        ).show()
        remindPurchaseInstant = zoneDateTime.toInstant()
    }


    private fun showCurrentLocationWithPermissionCheck(hasLocation: Boolean) {
        if (hasLocation) {
            LocationUpdatesDialog.newInstance(purchasesViewModel.getLocations())
                .show(childFragmentManager, "PurchaseFragment TAG")
        } else run {
            checkPermissionAndImplement(::showLocationInfo)
        }

    }

    override fun onAlarmButtonClick() {
        showRemindDateDialog = AlertDialog.Builder(requireContext())
            .setMessage("Установленное время напоминания: $remindPurchaseInstant")
            .setPositiveButton("Ok") { d, _ -> d.dismiss() }
            .show()
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
                       val locationInfo = """
                            Широта: ${it.latitude}
                            Долгота: ${it.longitude}
                            Высота: ${it.altitude}
                            Точность: ${it.accuracy}
                        """.trimIndent()
                        purchasesViewModel.addLocationInfo(locationInfo)
                        purchasesViewModel.changeArrayLocations()
                    }

                }
                .addOnCanceledListener {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.request_canceled),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.request_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun showLocationRationaleDialog() {
        needRationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.need_request_for_review))
            .setPositiveButton("Принять") { _, _ ->
                locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        confirmDeleteAlertDialog?.dismiss()
        confirmDeleteAlertDialog = null
        needRationaleDialog?.dismiss()
        needRationaleDialog = null
        showRemindDateDialog?.dismiss()
        showRemindDateDialog = null
        showLocationDialog?.dismiss()
        showLocationDialog = null
        apiDisabledDialog?.dismiss()
        apiDisabledDialog = null
    }

    companion object {
        const val KEY_LOCATION_INFO = "Key of the location info"
    }
}