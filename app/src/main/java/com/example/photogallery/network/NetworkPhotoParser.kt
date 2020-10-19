package com.example.photogallery.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import java.lang.NullPointerException
import java.net.URL
import java.util.concurrent.Executor
import java.util.logging.Handler

class NetworkPhotoParser(private val executor: Executor) {
    fun makeDownloadPhotoRequest(url: String, callback: (Bitmap) -> Unit){
        executor.execute {
            val inputStream = URL(url).openStream()
            val factory: Bitmap = BitmapFactory.decodeStream(inputStream)
            callback(factory)
        }
    }
}