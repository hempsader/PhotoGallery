package com.example.photogallery.network

import android.app.Application
import android.graphics.Bitmap
import android.util.LruCache
import com.example.photogallery.PhotoRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PhotoApplication (): Application(){
    override fun onCreate() {
        super.onCreate()
        PhotoRepository.initialise(applicationContext)
    }
}