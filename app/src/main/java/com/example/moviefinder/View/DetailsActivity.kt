package com.example.moviefinder.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.moviefinder.R
import com.example.moviefinder.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent;
        val title = intent.getStringExtra("title")
        val popularity = intent.getStringExtra("popularity")
        val releaseDate = intent.getStringExtra("releaseDate")
        val posterPath = intent.getStringExtra("posterPath")
        val backdropPath = intent.getStringExtra("backdropPath")
        val overview = intent.getStringExtra("overview")

        Glide.with(applicationContext).load(backdropPath).into(binding.backgroundImg)
        binding.movieOverview.text = overview
        binding.movieTitle.text = title
        binding.detailReleaseYear.text = releaseDate
        binding.collapsingBar.title = title
        binding.collapsingBar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.collapsingBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
    }


//    @BindingAdapter("imageUrl")
//    fun loadImage(view: ImageView, imageUrl: String?) {
//        Glide.with(view.context).load(imageUrl).into(view)
//    }
}