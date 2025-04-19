package com.eapvlab.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.eapvlab.movieapp.core.Resource
import com.eapvlab.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchUpCommingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getUpComingMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchTopRatedMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getTopRatedMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getPopularMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class MoviewViewModelFactory (private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
