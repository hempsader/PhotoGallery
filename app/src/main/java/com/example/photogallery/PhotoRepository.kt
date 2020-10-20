package com.example.photogallery

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import android.util.LruCache
import java.util.concurrent.Executors

enum class NetworkState{
    Connected,
    Dissconnected
}

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
        fun getLruChache() = LruCache<String, Bitmap>(1000)
        fun getExecutor() = Executors.newFixedThreadPool(8)
        fun netWorkState(context: Context,callbackStatus : ( NetworkState ) -> Unit){
            val currentNework = context.getSystemService(ConnectivityManager::class.java)
            val callback = currentNework.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    callbackStatus(NetworkState.Connected)
                    Log.d("aa", "network connected")
                }
                override fun onLost(network: Network) {
                    callbackStatus(NetworkState.Dissconnected)
                    Log.d("aa", "network dissconnected")
                }
            })
        }
    }


}