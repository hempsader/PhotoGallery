package com.example.photogallery.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class FlickrFetchr {
    private val flickrApi: FlickrApi
    init {
       val retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/").addConverterFactory(ScalarsConverterFactory.create()).build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }
    fun fetchContent(): LiveData<String>{
        val responseData: MutableLiveData<String> = MutableLiveData()
        val flickrHomeRequest: Call<String> = flickrApi.fetchContents()
        flickrHomeRequest.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                responseData.value = response.body()
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("aa","${t.message}")
            }
        })
        return responseData
    }
}