package com.example.moviefinder.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list?language=en")
    fun getGenres(): Call<GenreDBResponse>
}