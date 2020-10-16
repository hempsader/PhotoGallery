package com.example.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.api.FlickrApi
import com.example.photogallery.api.FlickrFetchr
import com.example.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment private constructor(): Fragment() {
    private lateinit var photoRecycler: RecyclerView
    companion object{
        fun getInstance(stringVal: String = ""): Fragment{
            return  PhotoGalleryFragment().apply {
                arguments = Bundle().apply {
                    putString("key", stringVal)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler,container,false)
        photoRecycler = view.findViewById(R.id.photo_recycler)
        photoRecycler.apply {
            layoutManager = GridLayoutManager(requireActivity(),3)
        }
        return view
    }

}