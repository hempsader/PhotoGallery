package com.example.photogallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.photogallery.api.FlickrApi

class PhotoLiveData : ViewModel(){
    var photosViewModel: PhotoGalleryViewModel
    init {
       photosViewModel = PhotoGalleryViewModel(FlickrApi.getApiService())
    }

    fun getData() = photosViewModel.getPhotosResult().cachedIn(viewModelScope)
}