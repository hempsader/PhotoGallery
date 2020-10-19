package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photogallery.network.PhotoApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PhotoApplication()
        setContentView(R.layout.activity_main)
        val ifFragmentContainerEmpty = supportFragmentManager.findFragmentByTag("galleryFragment")
        if(ifFragmentContainerEmpty == null ){
            supportFragmentManager.beginTransaction().add(R.id.container,PhotoGalleryFragment.getInstance(),"galleryFragment").commit()
        }

    }
}