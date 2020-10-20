package com.example.photogallery.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.photogallery.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlickrFetch(val flickrApi: FlickrApi) {

    fun searChPhotos(search: String): LiveData<List<Photo>> {
        val returnedList: MutableLiveData<List<Photo>> = MutableLiveData()
        flickrApi.searchPhotos(search).enqueue(object: Callback<PhotoResponse>{
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                returnedList.postValue(response.body()?.galleryItems)
            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.d("aa",t.message.toString())
            }
        })
        return returnedList
    }

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