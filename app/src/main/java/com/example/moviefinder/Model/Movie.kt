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
      @SerializedName("id") val id: Int?,
      @SerializedName("backdrop_path") val backdropPath: String,
      @SerializedName("title") val title: String?,
      @SerializedName("overview") val overview: String?,
      @SerializedName("poster_path") val posterPath: String?,
      @SerializedName("popularity") val popularity: Int?,
      @SerializedName("release_date") val releaseDate: String?
)
