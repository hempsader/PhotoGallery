package com.example.photogallery.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.WorkerThread

class FlickrFetch(val flickrApi: FlickrApi) {
    @WorkerThread
    fun fetchPhoto(url: String): Bitmap?
    {
        val response = flickrApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use {
            BitmapFactory.decodeStream(it)
        }
        return bitmap
    }
}