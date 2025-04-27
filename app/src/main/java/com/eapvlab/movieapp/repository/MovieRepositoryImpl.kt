package com.eapvlab.movieapp.repository

import com.eapvlab.movieapp.data.model.MovieList
import com.eapvlab.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSource: RemoteMovieDataSource): MovieRepository {

    override suspend fun getUpComingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}
