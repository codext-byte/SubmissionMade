package com.example.project.madesubmission.di

import com.example.project.core.domain.usecase.MovieAppInteractor
import com.example.project.core.domain.usecase.MovieAppUseCase
import com.example.project.madesubmission.detail.DetailViewModel
import com.example.project.madesubmission.home.SearchViewModel
import com.example.project.madesubmission.movies.MoviesViewModel
import com.example.project.madesubmission.tvshows.TvShowsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieAppUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}