package com.example.project.madesubmission.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.project.core.data.Resource
import com.example.project.core.domain.model.Movie
import com.example.project.core.domain.usecase.MovieAppUseCase

class MoviesViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieAppUseCase.getAllMovies(sort).asLiveData()
}