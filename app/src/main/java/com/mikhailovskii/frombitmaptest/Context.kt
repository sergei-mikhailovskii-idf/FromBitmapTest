/*
 * Copyright (c) Moneyman 2020.
 */

package com.mikhailovskii.frombitmaptest

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Originally Created by onexeor (Savchik Viktor) on 10/06/2020
 * for android-mx (mx.moneyman.extension)
 */


@Throws(IOException::class)
fun Context.createImageFile(): File? {
    val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmmss", Locale("es", "MX")).format(Date())
    val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}