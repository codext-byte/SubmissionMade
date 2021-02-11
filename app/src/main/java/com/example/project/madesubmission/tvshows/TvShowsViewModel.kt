package com.example.project.madesubmission.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.project.core.data.Resource
import com.example.project.core.domain.model.Movie
import com.example.project.core.domain.usecase.MovieAppUseCase

class TvShowsViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getTvShows(sort: String): LiveData<Resource<List<Movie>>> =
        movieAppUseCase.getAllTvShows(sort).asLiveData()
}