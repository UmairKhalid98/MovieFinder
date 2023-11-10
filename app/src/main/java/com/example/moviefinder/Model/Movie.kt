package com.example.moviefinder.Model
import com.google.gson.annotations.SerializedName

/**
 * Represents a Movie object with details fetched from a movie database API.
 *
 * @property id The unique identifier for the movie.
 * @property title The title of the movie.
 * @property overview A brief overview or summary of the movie's plot.
 * @property posterPath The path to the movie's poster image.
 * @property popularity The popularity score of the movie.
 * @property releaseDate The release date of the movie.
 *
 * @constructor Creates a new instance of the Movie data class.
 */
data class Movie(

      val id: Int,
      val name: String,
      val first_air_date: String,
      val popularity: Double,
      val backdrop_path: String,
      val poster_path: String,
      val overview: String
)
