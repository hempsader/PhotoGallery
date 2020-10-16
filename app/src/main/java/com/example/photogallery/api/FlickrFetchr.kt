package com.example.photogallery.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.photogallery.Photo
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FlickrFetchr {
    private val flickrApi: FlickrApi
    init {
       val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/").addConverterFactory(GsonConverterFactory.create()).build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }
    fun fetchPhoto(): LiveData<List<Photo>>{
        val responseData: MutableLiveData<List<Photo>> = MutableLiveData()
        val flickrHomeRequest: Call<FlickrResponse> = flickrApi.fetchPhoto()
        flickrHomeRequest.enqueue(object : Callback<FlickrResponse> {
            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                val entryPoint: FlickrResponse? = response.body()
                val photoResponse = entryPoint?.photos
                var galleryItems: List<Photo> = photoResponse?.galleryItems ?: mutableListOf()
                galleryItems = galleryItems.filterNot {
                    it.url.isBlank()
                }
                responseData.value = galleryItems
            }

            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.d("aaa", t.message.toString())
            }
        })
        return responseData
    }
}