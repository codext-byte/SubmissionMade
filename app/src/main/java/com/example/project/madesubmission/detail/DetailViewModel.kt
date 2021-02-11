package com.example.project.madesubmission.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.core.domain.model.Movie
import com.example.project.core.domain.usecase.MovieAppUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            movieAppUseCase.setMovieFavorite(movie, newStatus)
        }
    }
}
