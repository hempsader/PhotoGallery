package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ifFragmentContainerEmpty = supportFragmentManager.findFragmentByTag("galleryFragment")
        if(ifFragmentContainerEmpty == null ){
            supportFragmentManager.beginTransaction().add(R.id.container,PhotoGalleryFragment.getInstance(),"galleryFragment").commit()
        }

    }
}