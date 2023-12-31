package com.example.moviefinder.View

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinder.Model.Movie
import com.example.moviefinder.R


/**
 * RecyclerView Adapter for displaying a list of movies.
 *
 * @param movies The list of movies to be displayed.
 */
class MovieAdapter(private val movies: List<Movie>, private val getMovieGenres: (Movie) -> String) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var genreMap: Map<Int, String>? = null
    fun setGenreMap(genreMap: Map<Int, String>) {
        this.genreMap = genreMap
        notifyDataSetChanged()
    }
    /**
     * ViewHolder innerclass for displaying movie items.
     *
     * @param movieView The view representing a movie item.
     */
    class MovieViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView){

        val movieItemCardView: CardView = movieView.findViewById(R.id.movieItemCardView)
        val imagePoster: ImageView = movieView.findViewById(R.id.imagePoster)
        val movieTitle: TextView = movieView.findViewById(R.id.movieTitle)
        val popularity: TextView = movieView.findViewById(R.id.popularity)
        val releaseDate: TextView = movieView.findViewById(R.id.releaseDate)
        val genreIds:TextView = movieView.findViewById(R.id.genre)
    }

    /**
     * Creates a new ViewHolder when needed.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ViewHolder that holds a View of the given type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Binds the data at the specified position to the given [MovieViewHolder].
     *
     * @param holder The [MovieViewHolder] in which to bind the data.
     * @param position The position of the item within the data set.
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // Base URL for movie poster images
        val baseUrl:String = "https://image.tmdb.org/t/p/original"


        // Get the movie at the specified position
        val movie = movies[position]
        val posterUrl:String = baseUrl+movie.poster_path
        val backdropUrl:String = baseUrl+movie.backdrop_path

        // Load and display the movie poster using Glide library
        holder.imagePoster.let {
            Glide.with(it.context).load(posterUrl).override(200, 300).into(it)
        }
        // Set movie title, popularity and release dates
        holder.movieTitle.text = movie.name
        holder.popularity.text = "Popularity ${movie.popularity}"
        holder.releaseDate.text = "Released ${movie.first_air_date}"
        holder.genreIds.text = getMovieGenres(movie)


        holder.movieItemCardView.setOnClickListener{
            val intent = Intent(it.context,DetailsActivity::class.java )
            intent.putExtra("title",movie.name);
            intent.putExtra("popularity",movie.popularity);
            intent.putExtra("releaseDate",movie.first_air_date);
            intent.putExtra("posterPath",posterUrl);
            intent.putExtra("backdropPath",backdropUrl);
            intent.putExtra("overview",movie.overview);
            intent.putExtra("id",movie.id);
            intent.putExtra("Genre", getMovieGenres(movie))
            it.context.startActivity(intent);
        }

    }

}