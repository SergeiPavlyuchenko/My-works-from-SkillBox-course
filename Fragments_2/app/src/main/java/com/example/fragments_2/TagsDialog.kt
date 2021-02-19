package com.example.fragments_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class TagsDialog(tags: List<ArticleTag>) : DialogFragment() {

    private val booleans = BooleanArray(tags.size)
    private val positions = tags.map { it.position }.toTypedArray()

    private val fragment: Fragment?
        get() = parentFragment

    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment?.let { it as? DialogInterfaceListener }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Отфильтровать по позиции:")
            .setMultiChoiceItems(positions, booleans) { _, which, isChecked ->
               if (isChecked) tagsForFilter.add(ArticleTag.valueOf(positions[which]))
            }
            .setPositiveButton("Применить") {
                    _: DialogInterface, _: Int -> dialogInterfaceListener?.onConfirm()
            }
            .create()

    }

    companion object {
        val tagsForFilter: MutableList<ArticleTag> = mutableListOf()
    }
}