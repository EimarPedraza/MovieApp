package com.eapvlab.movieapp.data.local

import com.eapvlab.movieapp.application.AppConstants
import com.eapvlab.movieapp.data.model.MovieEntity
import com.eapvlab.movieapp.data.model.MovieList
import com.eapvlab.movieapp.data.model.toMovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalMovieDataSource(
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO)
{

    suspend fun getUpcomingMovies(): MovieList = withContext(ioDispatcher) {
        movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList = withContext(ioDispatcher) {
        movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList = withContext(ioDispatcher) {
        movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }

}