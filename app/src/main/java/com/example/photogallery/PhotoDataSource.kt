package com.example.photogallery

import androidx.paging.PagingSource
import com.example.photogallery.api.FlickrApi
import kotlin.Exception

class PhotoDataSource(private val apiService: FlickrApi): PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
       return try{
            val nextPage = params.key ?: 1
           val response = apiService.fetchPhoto(nextPage)
           val photos = response.galleryItems
           return LoadResult.Page(data = photos,null,nextPage+1)
        }catch (e:Exception){
           LoadResult.Error(e)
       }
    }


}

