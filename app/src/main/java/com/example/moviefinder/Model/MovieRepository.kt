package com.example.moviefinder.Model

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.lifecycle.liveData

/**
 * Repository class responsible for fetching movie data.
 *
 * @property context The application context used for accessing local assets.
 * @constructor Creates a new instance of the MovieRepository class.
 */
class MovieRepository(private val context: Context) {

    /**
     * Retrieves a LiveData object containing a list of movies from a local JSON file.
     *
     * @return LiveData<List<Movie>> The LiveData object representing the list of movies.
     */
    fun getMovieList(): LiveData<List<Movie>>{
        //convert the json data into string that can then be parsed by gson
        return liveData {
            val json = context.assets.open("localMoviesData.json").bufferedReader().use{it.readText()}
            val gson = Gson()
            val type = object : TypeToken<Map<String,Any>>() {}.type
            val data: Map<String, Any> = gson.fromJson(json,type)

            // Extract movie details from the JSON data
            //@Todo remove the 0 from id as it may cause problems
            val results = data["results"] as List<Map<String, Any>>
            val movies = results.map { Movie (
                it["id"] as? Int ?: 0,
                it["title"] as? String?:"",
                it["overview"] as? String?:"",
                it["poster_path"] as? String?:"",
                it["popularity"] as? Int ?: 0,
                it["release_date"] as? String ?: " {01-02-1234}"
            )}
            emit(movies)
        }
    }
}