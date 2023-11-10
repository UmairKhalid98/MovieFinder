package com.example.moviefinder.ViewModel

import android.content.Intent
import android.os.Debug
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    val movieList: MutableLiveData<List<Movie>?> = repository.getAPIMovies()
    val genreMap: MutableLiveData<Map<Int, String>> = MutableLiveData(mapOf())

    fun fetchGenres() {
        repository.fetchGenres()
        genreMap.value = repository.genreMap
    }
    fun getMovieGenres(movie: Movie): String {
        return movie.genre_ids.joinToString(", ") { id ->
            Log.d("Genre", "id in : " + id + "genreMap " + genreMap.value?.get(id))
            genreMap.value?.get(id) ?: ""
        }
    }
}