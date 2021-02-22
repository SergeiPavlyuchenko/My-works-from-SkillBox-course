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
import com.example.fragments_2.ArticleFragment.Companion.tags


class TagsDialog : DialogFragment() {

    private val fragment: Fragment?
        get() = parentFragment

    private val dialogInterfaceListener: DialogInterfaceListener?
        get() = fragment?.let { it as? DialogInterfaceListener }


    private val positions = tags.map { it.position }.toTypedArray()


    private val selectedItems: BooleanArray? by lazy {
        if (arguments != null) {
            arguments?.getBooleanArray(KEY_SELECTED)!!
        } else BooleanArray(tags.size) { true }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("TagsDialog", "onCreateDialog|${hashCode()}|$arguments")
        return dialogLaunch()
    }

    companion object {
        private const val KEY_SELECTED = "selected items boolean array"
        val tagsForFilter: MutableList<ArticleTag> = mutableListOf()
        fun newInstance(): TagsDialog {
            return TagsDialog().withArguments {
                putBooleanArray(KEY_SELECTED, TagsDialog().selectedItems)
            }
        }

    }


    private fun dialogLaunch(): Dialog {
        Log.d("TagsDialog", "dialogLaunch|${hashCode()}|$arguments")
        return AlertDialog.Builder(requireContext())
            .setTitle("Отфильтровать по позиции:")
            .setMultiChoiceItems(positions, selectedItems) { _, which, isChecked ->

                if (isChecked) {
                    selectedItems?.set(which, isChecked)
                    newInstance()
//                   tagsForFilter.add(ArticleTag.valueOf(positions[which]))
                } else {
                    selectedItems?.set(which, !isChecked)
                    newInstance()
                }
            }
            .setPositiveButton("Применить") { _: DialogInterface, _: Int ->
                dialogInterfaceListener?.onConfirm()
                newInstance()
            }
            .setNegativeButton("Отмена") { _: DialogInterface, _: Int ->
                dialog?.dismiss()
            }
            .create()
    }


























    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TagsDialog", "onAttach|${hashCode()}|$arguments")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TagsDialog", "onCreate|${hashCode()}")
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
        newInstance()
        Log.d("TagsDialog", "onDetach|${hashCode()}|$arguments")
    }
}