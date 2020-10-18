package com.example.photogallery.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.photogallery.Photo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.function.LongFunction

interface FlickrApi {

   @GET("services/rest/?method=flickr.interestingness.getList"+
           "&api_key=5f54e7f20048a04d31912e960ddc3f7e"+ "&extras=url_s"+ "&per_page="+ "&page=1" +
            "&format=json" + "&nojsoncallback=1")
   suspend fun fetchPhoto(@Query ("page") page: Int = 1): PhotoResponse

    companion object {
        private fun factory(): Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(PhotoResponse::class.java, PhotoDeserialiser())
            return gsonBuilder.create()
        }

        fun getApiService() =
            Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create(factory())).build()
                .create(FlickrApi::class.java)

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