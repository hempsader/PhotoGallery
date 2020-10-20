package com.example.photogallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.photogallery.api.FlickrApi
import com.example.photogallery.api.FlickrFetch

class PhotoLiveData : ViewModel(){
    var photosViewModel: PhotoGalleryViewModel
    var flickrFetch: FlickrFetch
    init {
       photosViewModel = PhotoGalleryViewModel(FlickrApi.getApiService())
        flickrFetch = FlickrFetch(FlickrApi.getApiService())
    }
    fun search(search: String){
        flickrFetch.searChPhotos(search)
    }
    fun getData() = photosViewModel.getPhotosResult().cachedIn(viewModelScope)
}