package com.example.photogallery.api

import android.util.Log
import com.example.photogallery.Photo
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class PhotoDeserialiser : JsonDeserializer<PhotoResponse>{

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {
        val jsonObjects = json?.asJsonObject
        val photosObject = jsonObjects?.get("photos")?.asJsonObject
        val jsonArray = photosObject?.get("photo")?.asJsonArray
        val listPhoto = mutableListOf<Photo>()
        for(photo in jsonArray!!){
            val getPhoto =  Photo(photo.asJsonObject.get("title").asString,photo.asJsonObject.get("id").asString,photo.asJsonObject.get("url_s").asString)
            listPhoto.add(getPhoto)
        }
        val photoResponse = PhotoResponse()
        photoResponse.galleryItems = listPhoto
        return photoResponse
    }

}