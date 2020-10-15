package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ifFragmentContainerEmpty = savedInstanceState == null
        if(ifFragmentContainerEmpty){
            val bundle = Bundle()
            bundle.putString("key","working")
            supportFragmentManager.beginTransaction().add(R.id.container,PhotoGalleryFragment().getInstance(bundle)).commit()
        }
    }
}