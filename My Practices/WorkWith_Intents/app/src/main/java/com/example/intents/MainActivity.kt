package com.skillbox.workwith_intents

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.intents.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) {
        it ?: toast("Result code was canceled")
        binding.resultPhotoImageView.setImageBitmap(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LifeCycleActivity", "MainActivity|onCreate|${hashCode()}")


    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycleActivity", "MainActivity|onStart|${hashCode()}")

    }


    //onResume
    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleActivity", "MainActivity|onResume|${hashCode()}")

        binding.startExplicitButton.setOnClickListener {
            startActivity(SecondActivity.getIntent(this, binding.editTextView.text.toString()))
        }

        binding.sendEmailButton.setOnClickListener {
            val isEmailValid =
                Patterns.EMAIL_ADDRESS.matcher(binding.emailAddressEditText.text.toString())
                    .matches()

            if (!isEmailValid) {
                toast("Incorrect Email")
            } else {
                composeEmail(
                    arrayOf(binding.emailAddressEditText.text.toString()),
                    binding.subjectEditText.text.toString()
                )
            }
        }

        binding.takePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

    }


    override fun onPause() {
        super.onPause()
        Log.d("LifeCycleActivity", "MainActivity|onPause|${hashCode()}")

    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycleActivity", "MainActivity|onStop|${hashCode()}")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycleActivity", "MainActivity|onDestroy|${hashCode()}")

    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            toast("No component to handle intent")
        }
    }

//    This is custom work code without this dependencies
//    implementation 'androidx.activity:activity:1.2.0-rc01'
//    implementation 'androidx.activity:activity-ktx:1.2.0-rc01'

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == PHOTO_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                val previewBitmap = data?.getParcelableExtra("data") as? Bitmap
//                binding.resultPhotoImageView.setImageBitmap(previewBitmap)
//            } else toast("Result code was canceled")
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//
//    }

    private fun dispatchTakePictureIntent() {

        val isCameraPermissionGranted =
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

        if (!isCameraPermissionGranted) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CODE
            )
        } else {

//    This is custom work code without this dependencies
//    implementation 'androidx.activity:activity:1.2.0-rc01'
//    implementation 'androidx.activity:activity-ktx:1.2.0-rc01'

//            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            cameraIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(cameraIntent, PHOTO_REQUEST_CODE)
//            }

//    This is code with this libraries
//    implementation 'androidx.activity:activity:1.2.0-rc01'
//    implementation 'androidx.activity:activity-ktx:1.2.0-rc01'

            cameraLauncher.launch(null)

        }


    }

    companion object {
        const val PHOTO_REQUEST_CODE = 333
        const val PERMISSION_REQUEST_CODE = 444
    }
}
