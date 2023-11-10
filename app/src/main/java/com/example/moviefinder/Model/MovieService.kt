package com.example.moviefinder.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("tv/popular?language=en-US&")
    fun getMovies(@Query("page")page: Int): Call<MovieDBResponse>

}