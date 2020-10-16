package com.example.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photogallery.api.FlickrFetchr

class PhotoGalleryViewModel: ViewModel() {
    val flikrLiveData: LiveData<List<Photo>>
    init {
        flikrLiveData = FlickrFetchr().fetchPhoto()
    }


}