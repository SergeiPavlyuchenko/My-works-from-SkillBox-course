package com.example.workwith_viewpager

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.workwith_viewpager.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity(R.layout.activity_dialog) {

    private val binding by viewBinding(ActivityDialogBinding::bind)

    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.showSimpleDialogButton.setOnClickListener { showSimpleDialog() }
        binding.showButtonDialogButton.setOnClickListener { showDialogWithButtons() }
        binding.showSingleChoiceDialogButton.setOnClickListener { showDialogWithSingleChoice() }
        binding.showCustomLayoutDialogButton.setOnClickListener { showDialogWithCustomLayout() }
        binding.showDialogFragmentButton.setOnClickListener { showDialogFragment() }
        binding.showBottomSheetFragmentButton.setOnClickListener { showBottomSheetDialog() }

    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    private fun showSimpleDialog() {
        //Вызываем диалог
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.text_SimpleDialog))
            .setMessage(getString(R.string.text_SimpleDialogMessage))
            .create()
            .show()
    }
    private fun showDialogWithButtons() {
        AlertDialog.Builder(this)
            .setTitle("Delete item")
            .setMessage("Are you sure?")
            //При установке кнопок в параметрах вызываем DialogInterface.OnClickListener(),
            // можно просто лямбда функцией
            //и т.к. мы будем создавать свой листенер для каждой кнопки то можно убрать
            //входящие параметры в нём - dialog, which
            .setPositiveButton("Yes") { _, _ -> toast("Clicked Yes") }
            .setNegativeButton("No") { _, _ -> toast("Clicked No") }
            .setNeutralButton("Neutral") { _, _ -> toast("Clicked Neutral") }
            .create()
            .show()
    }

    private fun showDialogWithSingleChoice() {
        val mailProviders = arrayOf("google", "yandex", "mailru", "rambler")
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.text_select_mail_provider))
            //Чтобы реализовать множественный диалог используем SetMultiChoiceItem
            .setItems(mailProviders) { _,which -> toast("Selected = ${mailProviders[which]}") }
            .show()

    }

    private fun showDialogWithCustomLayout() {
        dialog = AlertDialog.Builder(this)
            //Если мы хотим в нашей вьюшке установить иконку, текст и т.д
            // то нужно заинфлейтить лейаут, получить вьюшку, в ней что-то изменить и потом
            // уже установить её в диалог с помощью метода setView(view: View!)
            .setView(R.layout.dialog_attention)
            .setPositiveButton("Ok") {_,_ ->}
            .show()
    }

    //Делается для того, чтобы при повороте экрана и т.д. диалог не пропадал с ошибкой
    private fun showDialogFragment() {
        ConfirmationDialogFragment()
            .show(supportFragmentManager,"confirmationDialogTag")
    }

    //Если хотим скрыть диалог программно, без участия пользователя
    private fun hideDialog() {
        supportFragmentManager.findFragmentByTag("confirmationDialogTag")
            ?.let { it as? ConfirmationDialogFragment }
            ?.dismiss()
    }

    private fun showBottomSheetDialog() {
        BottomsheetExample()
            .show(supportFragmentManager, "tag")
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}