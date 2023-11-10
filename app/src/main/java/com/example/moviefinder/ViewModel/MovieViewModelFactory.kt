package com.example.moviefinder.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviefinder.Model.MovieRepository

/**
 * Factory class responsible for creating instances of [MovieViewModel].
 *
 * @property repository The [MovieRepository] instance used by the ViewModel.
 * @constructor Creates a new instance of [MovieViewModelFactory] with the provided [repository].
 */
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the specified ViewModel.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A newly created instance of the specified ViewModel.
     * @throws IllegalArgumentException If the specified [modelClass] is not assignable from [MovieViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
