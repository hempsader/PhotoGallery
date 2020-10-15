package com.example.photogallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class PhotoGalleryFragment : Fragment() {

    fun getInstance(bundle: Bundle): Fragment{
      return  Fragment().apply {
          arguments = bundle
      }
    }


    init {
        arguments.let {
            Log.d("aa",it?.getString("key").toString())
        }
    }
}