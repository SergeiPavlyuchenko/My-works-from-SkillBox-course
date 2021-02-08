package com.example.intents

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.intents.databinding.ActivityMainBinding
import java.security.AccessControlContext
import java.util.jar.Manifest


private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCallButton.setOnClickListener {
            getCall()
        }

    }

    companion object {
        const val KEY_MESSAGE = "Main activity key"
        const val DIAL_REQUEST_CODE = 777

        fun getIntent(context: Context, message: String? = null): Intent {
            return Intent(context, MainActivity::class.java)
                .putExtra(KEY_MESSAGE, message ?: "")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }


    private fun getCall() {
        val isPhoneValid = Patterns.PHONE
            .matcher(binding.phoneNumberEditTextView.text.toString())
            .matches()
        if (isPhoneValid) {
            dialPhoneNumber(binding.phoneNumberEditTextView.text.toString())
        } else Toast.makeText(this, "Incorrect phone", Toast.LENGTH_SHORT).show()
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        /*val isPhonePermissionGranted =
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        if (!isPhonePermissionGranted) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                123
            )
        } else {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, DIAL_REQUEST_CODE)
            }
        }*/


        //        Почему-то такой код с вызовом ACTION_DIAL не сработал
        //        *Всё сработало когда добавил запрос в манифесте
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, DIAL_REQUEST_CODE)
        } else {
            Toast.makeText(this, "No component to handle intent", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == DIAL_REQUEST_CODE) {
            count++
            val pluralString =
                resources.getQuantityString(R.plurals.text_numbers_of_times_of_call, count, count)
            binding.countDialTextView.text = pluralString
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}

