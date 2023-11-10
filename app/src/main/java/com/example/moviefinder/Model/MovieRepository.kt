package com.example.moviefinder.Model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviefinder.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository class responsible for fetching movie data.
 *
 * @property context The application context used for accessing local assets.
 * @constructor Creates a new instance of the MovieRepository class.
 */
class MovieRepository(private val context: Context) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val interceptor = Interceptor { chain ->
        val apiKey = "Bearer ${BuildConfig.MY_API_KEY}"
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", apiKey)
            .addHeader("accept", "application/json")
            .build()
        chain.proceed(newRequest)
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    private val movieService = retrofit.create(MovieService::class.java)

    fun getAPIMovies(): MutableLiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>?>()
        val numberOfPages = 2;
        for(i in 1..numberOfPages){

            movieService.getMovies(4).enqueue(object : Callback<MovieDBResponse> {
                override fun onResponse(
                    call: Call<MovieDBResponse>,
                    response: Response<MovieDBResponse>
                ) {
                    if (response.isSuccessful) {
                        val fetchedMovies = response.body()?.results
                        if (data.value != null && fetchedMovies != null) {
                            data.value = data.value!!.plus(fetchedMovies)
                        } else if (fetchedMovies != null) {
                            data.value = fetchedMovies
                        }
                    }

                }
                override fun onFailure(call: Call<MovieDBResponse>, t: Throwable) {
                    Log.e("MovieRepository", "There was an error fetching the movies", t)
                }
            })
        }

        return data
    }

    /**
     * Retrieves a LiveData object containing a list of movies from a local JSON file.
     *
     * @return LiveData<List<Movie>> The LiveData object representing the list of movies.
     */
//    fun getMovieList(): LiveData<List<Movie>>{
//        //convert the json data into string that can then be parsed by gson
//        return liveData {
//            val json = context.assets.open("localMoviesData.json").bufferedReader().use{it.readText()}
//            val gson = Gson()
//            val type = object : TypeToken<Map<String,Any>>() {}.type
//            val data: Map<String, Any> = gson.fromJson(json,type)
//
//            // Extract movie details from the JSON data
//            //@Todo remove the 0 from id as it may cause problems
//            val results = data["results"] as List<Map<String, Any>>
//            val movies = results.map { Movie (
//                it["id"] as? Int ?: 0,
//                it["backdrop_path"] as? String?:"",
//                it["title"] as? String?:"",
//                it["overview"] as? String?:"",
//                it["poster_path"] as? String?:"",
//                it["popularity"] as? Double ?: 0,
//                it["release_date"] as? String ?: " {01-02-1234}"
//            )}
//            emit(movies)
//        }
//    }
}