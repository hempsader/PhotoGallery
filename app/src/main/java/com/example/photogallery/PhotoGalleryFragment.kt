package com.example.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhotoGalleryFragment : Fragment() {
    private  val viewModelPhotoGallery by   viewModels<PhotoLiveData>()
    private lateinit var photoRecycler: RecyclerView
    companion object{
        fun getInstance(): PhotoGalleryFragment{
            return  PhotoGalleryFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        val view = inflater.inflate(R.layout.fragment_recycler,container,false)
        photoRecycler = view.findViewById(R.id.photo_recycler)
        photoRecycler.apply {
            layoutManager = GridLayoutManager(requireActivity(),3)
        }
        val adapter = PhotoRecycler(PhotoComparator)
        photoRecycler.adapter = adapter
        viewModelPhotoGallery.getData().observe(viewLifecycleOwner  ,{
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
        return view
    }
    object PhotoComparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
    inner class PhotoRecycler(diffCallback: DiffUtil.ItemCallback<Photo>): PagingDataAdapter<Photo,PhotoRecycler.ViewHolderPhoto>(diffCallback){
        inner class ViewHolderPhoto(itemView: View): RecyclerView.ViewHolder(itemView){
             val  textView : TextView
            init{
                textView = itemView as TextView
            }
            fun bind(photo: Photo){
                textView.text = photo.title
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhoto {
            return ViewHolderPhoto(TextView(parent.context))
        }
        override fun onBindViewHolder(holder: ViewHolderPhoto, position: Int) {
            val photo = getItem(position)
            holder.bind(photo!!)
        }
    }

}