 package com.example.activityresultapi

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForActivityResult(
                ActivityResultContracts.RequestPermission()) {}
                .launch(Manifest.permission.CAMERA)

        val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it)
                findViewById<ImageView>(R.id.image)?.setImageURI(uri)
            else
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.button)?.setOnClickListener {
            uri = getFileForCamera()
            takePicture.launch(uri)
        }
    }

    private fun getFileForCamera() =
            FileProvider.getUriForFile(this,
                    "${applicationContext.packageName}.provider",
                    File(filesDir, "${System.currentTimeMillis()}.jpg"))
}