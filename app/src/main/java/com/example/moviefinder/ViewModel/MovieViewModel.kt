package com.example.moviefinder.ViewModel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviefinder.Model.Movie
import com.example.moviefinder.Model.MovieRepository

/**
 * ViewModel responsible for managing and providing access to movie-related data.
 *
 * @property repository The [MovieRepository] instance used to retrieve movie data.
 * @property movieList LiveData representing the list of movies retrieved from the repository.
 * @constructor Creates a new instance of [MovieViewModel] with the provided [repository].
 */
class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    /**
     * LiveData representing the list of movies retrieved from the repository.
     */
    val movieList: LiveData<List<Movie>> = repository.getMovieList()
}