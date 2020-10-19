package com.example.photogallery

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import java.lang.IllegalStateException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PhotoRepository private constructor(val context: Context){

    companion object{
        private var photoRepository: PhotoRepository? = null
        fun initialise(context: Context){
            if(photoRepository == null){
                photoRepository = PhotoRepository(context)
            }
        }
        fun get(): PhotoRepository? {
            if(photoRepository != null){
                throw IllegalStateException("Already initialised")
            }else {
                return photoRepository
            }
        }
    }


}