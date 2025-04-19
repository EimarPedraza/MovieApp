package com.eapvlab.movieapp.data.remote

import com.eapvlab.movieapp.application.AppConstants
import com.eapvlab.movieapp.data.model.MovieList
import com.eapvlab.movieapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList {
        return webService.getUpComingMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

    suspend fun getTopRatedMovies(): MovieList {
        return webService.getTopRatedMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

    suspend fun getPopularMovies(): MovieList {
        return webService.getPopularMovies(AppConstants.API_KEY, AppConstants.LAN_ES_MX)
    }

}
