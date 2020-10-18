package com.example.photogallery

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import java.lang.IllegalStateException

class PhotoRepository {
    companion object{
        private var INITIALISE: PhotoRepository? = null
        fun initialise(context: Context){
            if(INITIALISE == null){
                INITIALISE = PhotoRepository()
            }
        }
        fun get():PhotoRepository{
            return INITIALISE ?: throw IllegalStateException("Already initialised")
        }
    }


}