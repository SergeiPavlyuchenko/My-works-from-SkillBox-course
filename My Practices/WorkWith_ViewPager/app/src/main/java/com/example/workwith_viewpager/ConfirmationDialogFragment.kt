package com.example.workwith_viewpager

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


//При работе с диалогами рекомендуемый способ - это DialogFragment, т.к. он сам управляет ЖЦ диалога
class ConfirmationDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  AlertDialog.Builder(requireContext())
            //В этом диалогФрагменте мы захаркодили создание диалога и тексты заголовка
            // и кнопок. Поэтому этот диалогФрагмент не получится переиспользовать во многих местах
            // Для того, что была возможность его переиспользовать нужно передавать данные из вне
            // аналогично работе с фрагментами - передавая тексты кнопок, заголовка и т.д в
            // аргументы фрагмента. А потом при создании диалога вытаскиваем данные из аргументов
            .setTitle("Delete item") //например так .setTitle(requireArguments().getString())
            .setMessage("Are you sure?")
            //Как диалог фрагмент будет оповещать активити о нажатие на кнопки?
            // тот же подход, что и с обычными фрагментами.
            // Создаётся интрефейс для взаимодействия с родительской сущностью,
            // Диалог фрагмент обращается к род. сущн. будь то активити или фрагмент,
            // пытается её скастовать к интерфейсу и вызвать необходимый метод
            .setPositiveButton("Yes") { _, _ -> } //например  { _, _ -> listener.onConfirm() }
            .setNegativeButton("No") { _, _ ->  }
            .setNeutralButton("Neutral") { _, _ ->  }
            .create()
    }
}

