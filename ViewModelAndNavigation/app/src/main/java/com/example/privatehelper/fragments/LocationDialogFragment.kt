package com.example.privatehelper.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.privatehelper.R
import com.example.privatehelper.extensions.withArguments

class LocationDialogFragment : DialogFragment(R.layout.dialog_location) {

    private lateinit var dialogViews: View
    private lateinit var locationInfoTextView: TextView
    private val args: LocationDialogFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogViews = requireActivity().layoutInflater.inflate(R.layout.dialog_location, null)
        locationInfoTextView = dialogViews.findViewById(R.id.locationInfoTextView)
        locationInfoTextView.text = args.locationInfo
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Ваши координаты:")
            .setView(dialogViews)
            .setPositiveButton("Ок", null)
            .create()
    }

    companion object {
        fun newInstance(locationInfo: String): DialogFragment {
            return LocationDialogFragment().withArguments {
                putString(PurchaseFragment.KEY_LOCATION_INFO, locationInfo)
            }
        }
    }

}