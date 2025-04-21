package com.eapvlab.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.eapvlab.movieapp.R
import com.eapvlab.movieapp.core.Resource
import com.eapvlab.movieapp.data.model.Movie
import com.eapvlab.movieapp.data.remote.MovieDataSource
import com.eapvlab.movieapp.databinding.FragmentMovieBinding
import com.eapvlab.movieapp.presentation.MovieViewModel
import com.eapvlab.movieapp.presentation.MovieViewModelFactory
import com.eapvlab.movieapp.repository.MovieRepositoryImpl
import com.eapvlab.movieapp.repository.RetrofitClient
import com.eapvlab.movieapp.ui.movie.adapters.MovieAdapter
import com.eapvlab.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.eapvlab.movieapp.ui.movie.adapters.concat.TopRaredConcatAdapter
import com.eapvlab.movieapp.ui.movie.adapters.concat.UpComingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieCLickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(MovieDataSource(RetrofitClient.webService))
        )
    }
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        //viewLifecycleOwner permite no recrear varios observers y evitar multiples peticiones a la vista
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    Log.d("LiveData", "UpComing: ${result.data.first}")
                    Log.d("LiveData", "TopRated: ${result.data.second}")
                    Log.d("LiveData", "Popular: ${result.data.third}")
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpComingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(0, TopRaredConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(0, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("LiveDatag", "Error: ${result.exception}")
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}
