package com.example.fragments_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.fragments_2.ArticleFragment.Companion.tags

class TagsDialog : DialogFragment() {

    private val fragment: Fragment?
        get() = parentFragment

    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment?.let { it as? DialogInterfaceListener }


    private var selectedItems = BooleanArray(tags.size)
    private val positions = tags.map { it.position }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedItems = if (arguments != null) {
            arguments?.getBooleanArray(KEY_SELECTED)!!
        } else BooleanArray(tags.size) { true }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setTitle("Отфильтровать по позиции:")
            .setMultiChoiceItems(positions, selectedItems) { _, which, isChecked ->

                if (isChecked) {
                    selectedItems[which] = isChecked
//                   tagsForFilter.add(ArticleTag.valueOf(positions[which]))
                } else selectedItems[which] = !isChecked
            }
            .setPositiveButton("Применить") { _: DialogInterface, _: Int ->
                dialogInterfaceListener?.onConfirm()
            }
            .setNegativeButton("Отмена") { _: DialogInterface, _: Int ->
                dialog?.dismiss()
            }
            .create()

    }

    companion object {
        private const val KEY_SELECTED = "selected items boolean array"
        val tagsForFilter: MutableList<ArticleTag> = mutableListOf()
    }


}