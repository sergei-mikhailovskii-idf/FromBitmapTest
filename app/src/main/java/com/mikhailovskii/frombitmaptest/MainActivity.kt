package com.mikhailovskii.frombitmaptest

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.google.mlkit.vision.common.InputImage

class MainActivity : AppCompatActivity() {

    private var uri: Uri? = null

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                createImageFile()?.also {
                    this.uri = FileProvider.getUriForFile(
                        this, "com.mikhailovskii.frombitmaptest.fileprovider", it
                    )
                    takePhotoFront.launch(uri)
                }
            }
        }

    private val takePhotoFront = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            uri?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                val a = InputImage.fromBitmap(bitmap, 0)
                println()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.btn).setOnClickListener {
            requestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }
}