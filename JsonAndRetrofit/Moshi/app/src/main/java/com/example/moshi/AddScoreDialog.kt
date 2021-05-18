package com.example.moshi

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class AddScoreDialog: BottomSheetDialogFragment() {

//    private val args: AddScoreDialogArgs by navArgs()

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
    private var adapterPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_score, null)
        stringScoreEditText = dialogView.findViewById(R.id.stringScoreEditText)
        completeDigitalScoreTextView = dialogView.findViewById(R.id.completeDigitalScoreTextView)
        addScoreButton = dialogView.findViewById(R.id.addScoreButton)
        stringScoreEditText = dialogView.findViewById(R.id.stringScoreEditText)
        digitalScore = dialogView.findViewById(R.id.digitalScore)

        val valuesItems = resources.getStringArray(R.array.Values)
        val digitalAdapter = ArrayAdapter(requireContext(), R.layout.item_list, valuesItems)
        (digitalScore.editText as? AutoCompleteTextView)?.setAdapter(digitalAdapter)

        bottomSheetDialog = BottomSheetDialog(requireContext())
        arguments?.getInt(POSITION_KEY)?.let { adapterPosition = it }

        addScoreButton.setOnClickListener {
            val score = stringScoreEditText.text.toString()
            val value = completeDigitalScoreTextView.text.toString()
            dialogInterfaceListener?.onItemChange(score,"$value/10", adapterPosition/*args.position*/)
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
    companion object {
        private const val POSITION_KEY = "Position Key"
        fun newInstance(position: Int): AddScoreDialog {
            return AddScoreDialog().apply {
                val args = Bundle().apply {
                    putInt(POSITION_KEY, position)
                }
                arguments = args
            }
        }
    }

}