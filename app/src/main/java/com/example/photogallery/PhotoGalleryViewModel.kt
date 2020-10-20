package com.example.photogallery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.photogallery.api.FlickrApi
import com.example.photogallery.api.FlickrFetch

class PhotoGalleryViewModel(private val flickrApi: FlickrApi) {
    fun getPhotosResult() =  Pager(PagingConfig(100)){PhotoDataSource(flickrApi)}.liveData
}