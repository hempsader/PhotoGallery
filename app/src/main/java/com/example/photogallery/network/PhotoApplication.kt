package com.example.photogallery.network

import android.app.Application
import com.example.photogallery.PhotoRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PhotoApplication (): Application(){
    val executorService: ExecutorService = Executors.newFixedThreadPool(8)
    override fun onCreate() {
        super.onCreate()
        PhotoRepository.initialise(applicationContext)
    }
}