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
import com.eapvlab.movieapp.presentation.MovieViewModelFactory
import com.eapvlab.movieapp.repository.MovieRepositoryImpl
import com.eapvlab.movieapp.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(MovieDataSource(RetrofitClient.webService))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        //viewLifecycleOwner permite no recrear varios observers y evitar multiples peticiones a la vista
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Succes -> {
                    Log.d("LiveData", "UpComing: ${result.data.first}")
                    Log.d("LiveData", "TopRated: ${result.data.second}")
                    Log.d("LiveData", "Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("LiveDatag", "Error: ${result.exception}")
                }
            }
        })
    }

}
