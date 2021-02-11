package com.example.project.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.project.core.domain.model.Movie
import com.example.project.core.domain.usecase.MovieAppUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<List<Movie>> =
        movieAppUseCase.getFavoriteMovies(sort).asLiveData()

    fun getFavoriteTvShows(sort: String): LiveData<List<Movie>> =
        movieAppUseCase.getFavoriteTvShows(sort).asLiveData()

    fun setFavorite(Movie: Movie, newState: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            movieAppUseCase.setMovieFavorite(Movie, newState)
        }
    }
}

