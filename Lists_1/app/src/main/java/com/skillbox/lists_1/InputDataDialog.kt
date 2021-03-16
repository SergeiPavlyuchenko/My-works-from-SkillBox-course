package com.skillbox.lists_1

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_1.databinding.DialogInputDataBinding
import kotlin.properties.Delegates

class InputDataDialog : DialogFragment() {

    private val fragment
        get() = parentFragment

    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment?.let { it as? DialogInterfaceListener }

    private lateinit var dialogViews: View
    private lateinit var nameEditText: EditText
    private lateinit var avatarLinkEditText: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var rateSpinner: Spinner
    private lateinit var isCoopCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogViews = requireActivity().layoutInflater.inflate(R.layout.dialog_input_data, null)
        nameEditText = dialogViews.findViewById(R.id.gameNameEditText)
        avatarLinkEditText = dialogViews.findViewById(R.id.avatarLinkEditText)
        genreSpinner = dialogViews.findViewById(R.id.gameGenresSpinner)
        rateSpinner = dialogViews.findViewById(R.id.rateGameSpinner)
        isCoopCheckBox = dialogViews.findViewById(R.id.coopModeCheckBox)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return dialogLaunch(/*name, avatarLink, genre, rate, isCoop*/)
    }

    private fun dialogLaunch(): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("            ${getString(R.string.text_enter_game_information)}")
            .setView(dialogViews)
            .setPositiveButton("Add") { _: DialogInterface, _: Int ->
                val name = nameEditText.text.toString()
                val avatarLink = avatarLinkEditText.text.toString()
                val genre = genreSpinner.selectedItem.toString()
                val rate = rateSpinner.selectedItem.toString().toFloat()
                val isCoop = isCoopCheckBox.isChecked
                val game: GameGenre = when (genre) {
                    getString(R.string.text_shooter) -> GameGenre.Shooters(
                        name,
                        avatarLink,
                        rate,
                        isCoop
                    )
                    getString(R.string.text_strategy) -> GameGenre.Strategy(
                        name,
                        avatarLink,
                        rate,
                        isCoop
                    )
                    getString(R.string.text_ccg) -> GameGenre.Ccg(name, avatarLink, rate)
                    else -> error("Incorrectly selected genre")
                }
                dialogInterfaceListener?.onConfirm(game)
            }
            .create()
    }
}