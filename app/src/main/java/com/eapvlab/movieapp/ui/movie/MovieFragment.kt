package com.eapvlab.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.eapvlab.movieapp.R
import com.eapvlab.movieapp.core.Resource
import com.eapvlab.movieapp.data.remote.MovieDataSource
import com.eapvlab.movieapp.databinding.FragmentMovieBinding
import com.eapvlab.movieapp.presentation.MovieViewModel
import com.eapvlab.movieapp.presentation.MoviewViewModelFactory
import com.eapvlab.movieapp.repository.MovieRepositoryImpl
import com.eapvlab.movieapp.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MoviewViewModelFactory(
            MovieRepositoryImpl(MovieDataSource(RetrofitClient.webService))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        //viewLifecycleOwner permite no recrear varios observers y evitar multiples peticiones a la vista
        viewModel.fetchUpCommingMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result){
                is Resource.Loading -> {
                    Log.d("LiveData-UpComming", "Loading...")
                }
                is Resource.Succes -> {
                    Log.d("LiveData-UpComming", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData-UpComming", "Error: ${result.exception}")
                }
            }
        })

        viewModel.fetchTopRatedMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result){
                is Resource.Loading -> {
                    Log.d("LiveData-TopRated", "Loading...")
                }
                is Resource.Succes -> {
                    Log.d("LiveData-TopRated", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData-TopRated", "Error: ${result.exception}")
                }
            }
        })

        viewModel.fetchPopularMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result){
                is Resource.Loading -> {
                    Log.d("LiveData-Popular", "Loading...")
                }
                is Resource.Succes -> {
                    Log.d("LiveData-Popular", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData-Popular", "Error: ${result.exception}")
                }
            }
        })
    }

}
