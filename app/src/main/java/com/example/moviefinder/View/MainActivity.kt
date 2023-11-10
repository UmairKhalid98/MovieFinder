package com.example.moviefinder.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviefinder.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add HomeFragment to the activity if savedInstanceState is null
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_fragment, HomeFragment())
                .commit()
        }
    }
}