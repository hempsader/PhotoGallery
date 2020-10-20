package com.example.photogallery.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.photogallery.NetworkState
import com.example.photogallery.PhotoRepository
import java.net.URL
import java.util.concurrent.Executor

class NetworkPhotoParser(private val executor: Executor) {
    fun makeDownloadPhotoRequest(url: String, callback: (Bitmap) -> Unit){
                executor.execute {
                    val inputStream = URL(url).openStream()
                    val factory: Bitmap = BitmapFactory.decodeStream(inputStream)
                    callback(factory)
                }
        }
}