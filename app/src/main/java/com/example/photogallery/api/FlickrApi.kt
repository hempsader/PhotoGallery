package com.example.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

   @GET("services/rest/?method=flickr.interestingness.getList"+
           "&api_key=faf780f78ea31f3945483b401e85a02f"+ "&extras=url_s"+ "&format=json"+
            "&nojsoncallback=1")
    fun fetchPhoto(): Call<FlickrResponse>
}