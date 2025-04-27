package com.eapvlab.movieapp.data.remote

import com.eapvlab.movieapp.application.AppConstants
import com.eapvlab.movieapp.data.model.MovieList
import com.eapvlab.movieapp.repository.WebService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMovieDataSource(
    private val webService: WebService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getUpcomingMovies(): MovieList = withContext(ioDispatcher) {
        webService.getUpComingMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

    suspend fun getTopRatedMovies(): MovieList = withContext(ioDispatcher) {
        webService.getTopRatedMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

    suspend fun getPopularMovies(): MovieList = withContext(ioDispatcher) {
        webService.getPopularMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

}
