package com.example.privatehelper.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.privatehelper.R
import com.example.privatehelper.extensions.withArguments

class LocationUpdatesDialog : DialogFragment(R.layout.dialog_location_updates) {

    private var newLocations: Array<String> = emptyArray()
    private var locations: Array<String> = emptyArray()
    private lateinit var dialogViews: View
    private lateinit var containerLocationTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogViews = requireActivity().layoutInflater.inflate(R.layout.dialog_location_updates, null)
        containerLocationTextView = dialogViews.findViewById(R.id.containerLocationTextView)
        arguments?.getStringArray(PurchaseFragment.KEY_LOCATION_INFO)?.let {
            newLocations = it
            locations = newLocations + locations
            containerLocationTextView.text = locations.joinToString("\n\n\n")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(dialogViews)
            .setMessage(getString(R.string.look_to_the_all_location_of_purchase))
            .setPositiveButton("Ok", null)
            .create()
    }

    companion object {
        fun newInstance(locations: Array<String>): LocationUpdatesDialog {
            return LocationUpdatesDialog().withArguments {
                putStringArray(PurchaseFragment.KEY_LOCATION_INFO, locations)
            }
        }
    }

}