package com.example.photogallery.api

import androidx.lifecycle.LiveData
import com.example.photogallery.Photo
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<Photo>
}