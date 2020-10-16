package com.example.photogallery

import com.google.gson.annotations.SerializedName

data class Photo(var title: String = "", var id: String = "", @SerializedName("url_s") var url: String = "")