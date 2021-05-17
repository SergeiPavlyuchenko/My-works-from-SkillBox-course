package com.example.moshi

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class AddScoreDialog: BottomSheetDialogFragment() {

    private val fragment
        get() = parentFragment
    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment.let { it as? DialogInterfaceListener }


    private lateinit var dialogView: View
    private lateinit var stringScore: EditText
    private lateinit var completeDigitalScoreTextView: TextView
    private lateinit var addScoreButton: Button
    private lateinit var digitalScore: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_score, null)
        stringScore = dialogView.findViewById(R.id.stringScoreEditText)
        completeDigitalScoreTextView = dialogView.findViewById(R.id.completeDigitalScoreTextView)
        addScoreButton = dialogView.findViewById(R.id.addScoreButton)
        stringScore = dialogView.findViewById(R.id.stringScoreEditText)
        digitalScore = dialogView.findViewById(R.id.digitalScore)
        val valuesItems = resources.getStringArray(R.array.Values)
        val digitalAdapter = ArrayAdapter(requireContext(), R.layout.item_list, valuesItems)
        (digitalScore.editText as? AutoCompleteTextView)?.setAdapter(digitalAdapter)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogView)
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addScoreButton.setOnClickListener {
            dialogInterfaceListener?.onItemChange()
        }
    }

}