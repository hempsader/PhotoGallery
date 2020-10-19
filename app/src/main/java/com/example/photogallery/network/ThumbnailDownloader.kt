package com.example.photogallery.network

import android.os.HandlerThread
import retrofit2.http.Url

class ThumbnailDownloader<in T> : HandlerThread("thread"){
    private var hasQuit = false

    override fun quit(): Boolean {
        hasQuit = true
        return super.quit()
    }

    fun queueThumbnail(target: T,url: Url){

    }
}