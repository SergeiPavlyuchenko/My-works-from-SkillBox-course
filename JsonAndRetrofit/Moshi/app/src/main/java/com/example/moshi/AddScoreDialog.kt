package com.example.moshi

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class AddScoreDialog : BottomSheetDialogFragment() {

    private val args: AddScoreDialogArgs by navArgs()

    private val fragment
        get() = parentFragment
    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment.let { it as? DialogInterfaceListener }

    private lateinit var dialogView: View
    private lateinit var stringScoreEditText: EditText
    private lateinit var completeDigitalScoreTextView: TextView
    private lateinit var addScoreButton: Button
    private lateinit var digitalScore: TextInputLayout
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var navController: NavController
//    private var adapterPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_score, null)
        stringScoreEditText = dialogView.findViewById(R.id.stringScoreEditText)
        completeDigitalScoreTextView = dialogView.findViewById(R.id.completeDigitalScoreTextView)
        addScoreButton = dialogView.findViewById(R.id.addScoreButton)
        stringScoreEditText = dialogView.findViewById(R.id.stringScoreEditText)
        digitalScore = dialogView.findViewById(R.id.digitalScore)
        navController = findNavController()

        val valuesItems = resources.getStringArray(R.array.Values)
        val digitalAdapter = ArrayAdapter(requireContext(), R.layout.item_list, valuesItems)
        (digitalScore.editText as? AutoCompleteTextView)?.setAdapter(digitalAdapter)

        bottomSheetDialog = BottomSheetDialog(requireContext())
//        arguments?.getInt(POSITION_KEY)?.let { adapterPosition = it }

        addScoreButton.setOnClickListener {
            val newScore = NewScore(
            stringScoreEditText.text.toString(),
            completeDigitalScoreTextView.text.toString()
            )
            val currentItemState = CurrentItemState(
                args.currentItemState.currentMovieList,
                args.currentItemState.adapterPosition
            )
            val savedState = navController.previousBackStackEntry?.savedStateHandle
            savedState?.set(MainFragment.NEW_SCORE_KEY, newScore)
            savedState?.set(MainFragment.ITEM_STATE_KEY, currentItemState)

            /*val action = AddScoreDialogDirections.actionAddScoreDialogToMainFragment(
                score,
                "$value/10",
                args.position
            )
            findNavController().navigate(action)*/
            /*  dialogInterfaceListener?.onItemChange(
                  score,
                  "$value/10",
                  args.position*//*adapterPosition*//*
            )*/
            bottomSheetDialog.dismiss()
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog.setContentView(dialogView)
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    /*companion object {
        private const val POSITION_KEY = "Position Key"
        fun newInstance(position: Int): AddScoreDialog {
            return AddScoreDialog().apply {
                val args = Bundle().apply {
                    putInt(POSITION_KEY, position)
                }
                arguments = args
            }
        }
    }*/

}