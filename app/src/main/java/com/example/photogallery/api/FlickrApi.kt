package com.example.photogallery.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.photogallery.Photo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.function.LongFunction

interface FlickrApi {
    //https://www.flickr.com/services/api/explore/flickr.photos.search
    @GET("services/rest?method=flickr.interestingness.getList")
   suspend fun fetchPhoto(@Query ("page") page: Int = 1): PhotoResponse

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

    @GET("services/rest?method=flickr.photos.search")
    fun searchPhotos(@Query("text") query: String): Call<PhotoResponse>

    companion object {
        private fun factory(): Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(PhotoResponse::class.java, PhotoDeserialiser())
            return gsonBuilder.create()
        }

        fun getApiService(): FlickrApi {
            val client = OkHttpClient.Builder().addInterceptor(PhotoInterceptor()).build()
            return Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create(factory())).client(client).build()
                .create(FlickrApi::class.java)
        }

     /*  private fun getPhotos(page: Int): MutableLiveData<List<Photo>> {
            val list: MutableLiveData<List<Photo>> = MutableLiveData()
            getApiService().fetchPhoto(page).enqueue(object : Callback<PhotoResponse> {
                override fun onResponse(
                    call: Call<PhotoResponse>,
                    response: Response<PhotoResponse>
                ) {
                    list.value = response.body()?.galleryItems
                    Log.d("aa", response.body()?.galleryItems.toString())
                }

                override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                    Log.d("aa", t.message.toString())
                }
            })
            return list
       */
    }
}