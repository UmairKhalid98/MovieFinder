package com.example.moviefinder.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.Model.MovieRepository
import com.example.moviefinder.R
import com.example.moviefinder.ViewModel.MovieViewModel
import com.example.moviefinder.ViewModel.MovieViewModelFactory
import com.example.moviefinder.databinding.FragmentHomeBinding

/**
 * HomeFragment displays a list of movies on the home screen using a RecyclerView.
 * It utilizes the MovieViewModel to manage and observe the list of movies.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    // ViewModel and Adapter instances
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    /**
     * Called when the fragment is created.
     * Any setup or initialization code for the fragment's lifecycle can be placed here.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /**
     * Called to create the view hierarchy associated with the fragment.
     * Inflates the layout for this fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false )
        return binding.root
    }

    /**
     * Called after the view has been created.
     * Initializes the ViewModel, RecyclerView, and observes changes in the movie list.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Creating MovieRepository and MovieViewModelFactory instances
        val repository = MovieRepository(requireContext())
        val factory = MovieViewModelFactory(repository)

        // Initializing MovieViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        // Initializing RecyclerView and its layout manager
        adapter = MovieAdapter(listOf())
        val recyclerView: RecyclerView = view.findViewById(R.id.movieRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        // Observing changes in the movieList LiveData from the ViewModel
        viewModel.movieList.observe(viewLifecycleOwner) { movies ->
            // Updating the adapter with the latest list of movies
            adapter = MovieAdapter(movies)
            recyclerView.adapter = adapter
        }

    }

}