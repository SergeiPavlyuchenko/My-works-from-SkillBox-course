package com.example.privatehelper.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import com.example.privatehelper.R
import com.example.privatehelper.extensions.UpdatedItem
import com.example.privatehelper.extensions.withArguments
import com.example.privatehelper.interfaces.DialogInterfaceListener

class DetailItemDialog : DialogFragment(R.layout.dialog_detail_item) {

    private lateinit var dialogViews: View
    private lateinit var linearLayout: LinearLayout
    private lateinit var mainDialog: AlertDialog
    private lateinit var customTitle: View
    private var itemPosition: Int = 0
    private lateinit var notCompletedPurchasesList: List<String>
    private lateinit var completedPurchasesList: List<SpannableString>

    //    private val args: DetailItemDialogArgs by navArgs()
    private val fragment
        get() = parentFragment
    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment.let { it as? DialogInterfaceListener }


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogViews = requireActivity().layoutInflater.inflate(R.layout.dialog_detail_item, null)
        linearLayout = dialogViews.findViewById(R.id.containerDetailItem)
        customTitle =
            requireActivity().layoutInflater.inflate(R.layout.title_custom_for_detail_dialog, null)
//        modifyDialogView(args.purchasesStringList)
        arguments?.getParcelable<UpdatedItem>(UPDATED_ITEM_KEY)?.let {
            itemPosition = it.position
            notCompletedPurchasesList = it.notCompletedPurchasesList
            completedPurchasesList = it.completedPurchasesList
        }
        modifyDialogView(notCompletedPurchasesList, completedPurchasesList)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mainDialog = AlertDialog.Builder(requireContext())
            .setView(dialogViews)
            .setCustomTitle(customTitle)
            .setTitle(getString(R.string.purchases_list))
            .create()
        return mainDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCheckedImplementation()
    }

    private fun modifyDialogView(
        notCompletedPurchasesList: List<String>,
        completedPurchasesList: List<SpannableString>
    ) {
        val tempNotCompletedPurchasesList: MutableList<String> = mutableListOf()
        val tempCompletedPurchasesList: MutableList<SpannableString> = mutableListOf()
        notCompletedPurchasesList.forEach {
            val checkBox = CheckBox(requireContext()).apply {
                text = it.trim()
                textSize = 22f
                buttonTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.color_checkboxes)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            linearLayout.addView(checkBox)
        }
        completedPurchasesList.forEach {
            val checkBox = CheckBox(requireContext()).apply {
                text = it.trim()
                textSize = 22f
                isChecked = true
                buttonTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.color_checkboxes)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.light_green))
            }
            linearLayout.addView(checkBox)
        }

        val confirmButton: Button = Button(requireContext()).apply {
            text = resources.getString(R.string.ok)
            width = LinearLayout.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }
        linearLayout.addView(confirmButton)


        confirmButton.setOnClickListener {
            val isCompletePurchases: MutableMap<String, Boolean> = mutableMapOf()

            linearLayout
                .children
                .mapNotNull { it as? CheckBox }
                .forEach {
                    isCompletePurchases[it.text.toString()] = it.isChecked
                }

            isCompletePurchases.forEach {

                if (!it.value) {
                    tempNotCompletedPurchasesList.add(it.key)
                } else {
                    val spannable = SpannableString(it.key)
                    spannable.setSpan(
                        StrikethroughSpan(),
                        0,
                        spannable.length,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    tempCompletedPurchasesList.add(spannable)
                }
            }
            this.notCompletedPurchasesList = tempNotCompletedPurchasesList
            this.completedPurchasesList = tempCompletedPurchasesList

            val spanBuilder = SpannableStringBuilder()

            tempNotCompletedPurchasesList.forEachIndexed { index, item ->
                spanBuilder.append(item)
                if(index != notCompletedPurchasesList.lastIndex) spanBuilder.append(", ")
            }
            tempCompletedPurchasesList.forEachIndexed { index, item ->
                spanBuilder.append(item)
                if(index != completedPurchasesList.lastIndex) spanBuilder.append(", ")
            }


            dialogInterfaceListener?.onConfirm(
                itemPosition,
                this.notCompletedPurchasesList,
                this.completedPurchasesList
            )
            mainDialog.dismiss()
        }
    }

    private fun isCheckedImplementation() {
        linearLayout
            .children
            .mapNotNull { it as? CheckBox }
            .forEach { checkBox ->
                checkBox.setOnClickListener {
                    if ((it as CheckBox).isChecked) {
                        it.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.light_green
                            )
                        )
                        val spannedString = SpannableString(it.text)
                        spannedString.setSpan(
                            StrikethroughSpan(),
                            0,
                            spannedString.lastIndex,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        )
                        it.text = spannedString
                    } else {
                        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        it.text = it.text.toString()
                    }
                }

            }
    }

    companion object {

        const val UPDATED_ITEM_KEY = "Updated item key"


        fun newInstance(
            position: Int,
            notCompletedPurchasesList: List<String>,
            completedPurchasesList: List<SpannableString>
        ): DetailItemDialog {
            return DetailItemDialog().withArguments {
                putParcelable(
                    UPDATED_ITEM_KEY,
                    UpdatedItem(position, notCompletedPurchasesList, completedPurchasesList)
                )
            }
        }
    }

}