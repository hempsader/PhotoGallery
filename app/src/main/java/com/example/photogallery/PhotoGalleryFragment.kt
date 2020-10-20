package com.example.photogallery

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.network.NetworkPhotoParser
import com.example.photogallery.network.PhotoApplication

class PhotoGalleryFragment : Fragment() {
    private  val viewModelPhotoGallery by   viewModels<PhotoLiveData>()
    private lateinit var networkPhotoParser: NetworkPhotoParser
    private lateinit var photoRecycler: RecyclerView
    private var haveNetwork = false
    companion object{
        fun getInstance(): PhotoGalleryFragment{
            return  PhotoGalleryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkPhotoParser = NetworkPhotoParser(PhotoRepository.getExecutor())
        PhotoRepository.netWorkState(requireContext()){
            haveNetwork = it == NetworkState.Connected
            Log.d("aa",haveNetwork.toString())
        }
        setHasOptionsMenu(true)
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
             val  image : ImageView
            init{
                image = itemView as ImageView
            }
            fun bind(photo: Photo, haveNetwork: Boolean){
                //Picasso.get().load(photo.url).into(image)
                if(haveNetwork) {
                    networkPhotoParser.makeDownloadPhotoRequest(photo.url) {
                        Handler(Looper.getMainLooper()).post {
                            image.setImageBitmap(it)
                        }
                    }
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhoto {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.phot_item_list,parent,false)
            return ViewHolderPhoto(view)
        }
        override fun onBindViewHolder(holder: ViewHolderPhoto, position: Int) {
            val photo = getItem(position)
            holder.bind(photo!!,haveNetwork)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = inflater.inflate(R.menu.gallery_toolbar_items,menu)
        return inflater
    }

}