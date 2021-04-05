package com.skillbox.lists_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import kotlin.random.Random.Default.nextLong

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
    private lateinit var isCoopTextView: TextView
    private lateinit var createRandomGameButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogViews = requireActivity().layoutInflater.inflate(R.layout.dialog_input_data, null)
        nameEditText = dialogViews.findViewById(R.id.gameNameEditText)
        avatarLinkEditText = dialogViews.findViewById(R.id.avatarLinkEditText)
        genreSpinner = dialogViews.findViewById(R.id.gameGenresSpinner)
        rateSpinner = dialogViews.findViewById(R.id.rateGameSpinner)
        isCoopCheckBox = dialogViews.findViewById(R.id.isCoopCheckBox)
        isCoopTextView = dialogViews.findViewById(R.id.isCoopTextView)
        createRandomGameButton = dialogViews.findViewById(R.id.createRandomGameButton)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        ccgIsNotCoopControl()
        createRandomGameButton.setOnClickListener {
            createRandomGame(AppData.randomGames.random())
        }
        return dialogLaunch()
    }


    private fun dialogLaunch(): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("            ${getString(R.string.text_enter_game_information)}")
            .setView(dialogViews)
            .setPositiveButton("Add") { _: DialogInterface, _: Int ->
                dialogInterfaceListener?.onConfirm(createGame())
            }
            .create()
    }

    private fun createGame(): GameGenre {
        val id = nextLong()
        val name = nameEditText.text.toString()
        val avatarLink = avatarLinkEditText.text.toString()
        val genre = genreSpinner.selectedItem.toString()
        val rate = rateSpinner.selectedItem.toString().toFloat()
        val isCoop = isCoopCheckBox.isChecked

        return when (genre) {
            getString(R.string.text_shooter) -> GameGenre.Shooters(
                id,
                name,
                avatarLink,
                rate,
                genre,
                isCoop
            )
            getString(R.string.text_strategy) -> GameGenre.Strategy(
                id,
                name,
                avatarLink,
                rate,
                genre,
                isCoop
            )
            getString(R.string.text_ccg) -> GameGenre.Ccg(id, name, avatarLink, rate, genre)
            else -> error("Incorrectly selected genre")
        }
    }

    private fun createRandomGame(game: GameGenre) {
        when (game) {
            is GameGenre.Shooters -> {
                resources.getStringArray(R.array.rateGame).forEachIndexed { index, s ->
                    if (s.toFloat() == game.rate) rateSpinner.setSelection(index)
                }
                nameEditText.setText(game.name)
                avatarLinkEditText.setText(game.avatarLink)
                genreSpinner.setSelection(0, true)
                isCoopCheckBox.isChecked = game.isCoop
            }
            is GameGenre.Strategy -> {
                resources.getStringArray(R.array.rateGame).forEachIndexed { index, s ->
                    if (s.toFloat() == game.rate) rateSpinner.setSelection(index)
                }
                nameEditText.setText(game.name)
                avatarLinkEditText.setText(game.avatarLink)
                genreSpinner.setSelection(1, true)
                isCoopCheckBox.isChecked = game.isCoop
            }
            is GameGenre.Ccg -> {
                resources.getStringArray(R.array.rateGame).forEachIndexed { index, s ->
                    if (s.toFloat() == game.rate) rateSpinner.setSelection(index)
                }
                nameEditText.setText(game.name)
                avatarLinkEditText.setText(game.avatarLink)
                genreSpinner.setSelection(2, true)
                isCoopCheckBox.isChecked = false
            }
            else -> error("Incorrect random game")
        }
    }

    private fun ccgIsNotCoopControl() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.gameGenres)
        )
        genreSpinner.adapter = arrayAdapter
        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val genre = genreSpinner.selectedItem.toString()
                if (genre == getString(R.string.text_ccg)) {
                    isCoopCheckBox.isChecked = false
                    isCoopCheckBox.isEnabled = false
                    isCoopTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    isCoopTextView.paintFlags = 0
                    isCoopCheckBox.isEnabled = true
                }

            }



            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }
}