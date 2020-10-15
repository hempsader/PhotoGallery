package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ifFragmentContainerEmpty = savedInstanceState == null
        if(ifFragmentContainerEmpty){

            supportFragmentManager.beginTransaction().add(R.id.container,PhotoGalleryFragment.getInstance()).commit()
        }

    }
}