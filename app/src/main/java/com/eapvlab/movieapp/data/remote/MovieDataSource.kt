package com.eapvlab.movieapp.data.remote

import com.eapvlab.movieapp.data.model.MovieList

class MovieDataSource {

    fun getUpcomingMovies(): MovieList {
        return MovieList()
    }
    
    fun getTopRatedMovies(): MovieList {
        return MovieList()
    }

    fun getPopularMovies(): MovieList {
        return MovieList()
    }

}
