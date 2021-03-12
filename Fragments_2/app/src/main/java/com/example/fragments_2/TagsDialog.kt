package com.example.fragments_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment


class TagsDialog : DialogFragment() {

    private val fragment: Fragment?
        get() = parentFragment

    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment?.let { it as? DialogInterfaceListener }

    private var selectedItems: BooleanArray = BooleanArray(AppData.TAGS.size)

    private val positions = AppData.TAGS.map { it.position }.toTypedArray()

    private var checkedTagsMap: MutableMap<ArticleTag, Boolean> =
        AppData.TAGS.zip(selectedItems.toTypedArray()).toMap().toMutableMap()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arguments?.getBooleanArray(KEY_SELECTED)?.let {
            checkedTagsMap.clear()
            selectedItems = it
            checkedTagsMap = AppData.TAGS.zip(selectedItems.toTypedArray()).toMap().toMutableMap()
            return dialogLaunch(selectedItems, checkedTagsMap)
        }
        return dialogLaunch(selectedItems, checkedTagsMap)
    }



    private fun dialogLaunch(selectedItems: BooleanArray, checkedTagsMap: MutableMap<ArticleTag, Boolean>): Dialog {
        Log.d("TagsDialog", "dialogLaunch|${hashCode()}|$arguments")
        return AlertDialog.Builder(requireContext())
            .setTitle("Отфильтровать по позиции:")
            .setMultiChoiceItems(positions, selectedItems) { _, which, isChecked ->
                    selectedItems[which] = isChecked
            }
            .setPositiveButton("Применить") { _: DialogInterface, _: Int ->
                val selectedTags = AppData.TAGS
                    .zip(selectedItems.toTypedArray())
                    .filter { it.second }
                    .map { it.first }
                dialogInterfaceListener?.onConfirm(selectedItems, selectedTags)
            }
            .setNegativeButton("Отмена") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .create()
    }

    fun newInstance(selectedPositionsTags: BooleanArray): TagsDialog {
        return TagsDialog().withArguments {
            putBooleanArray(KEY_SELECTED, selectedPositionsTags)
        }
    }

    companion object {
        const val KEY_SELECTED = "selected items from DialogFragment"
    }



















    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TagsDialog", "onAttach|${hashCode()}|$arguments")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TagsDialog", "onCreateView|${hashCode()}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("TagsDialog", "onActivityCreated|${hashCode()}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TagsDialog", "onStart|${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TagsDialog", "onResume|${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TagsDialog", "onPause|${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TagsDialog", "onStop|${hashCode()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TagsDialog", "onDestroyView|${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TagsDialog", "onDestroy|${hashCode()}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("TagsDialog", "onDetach|${hashCode()}|$arguments")
    }
}