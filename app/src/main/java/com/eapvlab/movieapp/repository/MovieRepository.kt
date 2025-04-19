package com.eapvlab.movieapp.repository

import com.eapvlab.movieapp.data.model.MovieList

interface MovieRepository {

    suspend fun getUpComingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList

}
