package com.framgia.kotlintraining.moviedb.di

import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.ui.RedditVewModel
import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.ui.MovieViewModel
import com.framgia.kotlintraining.moviedb.screen.detail.DetailMovieViewModel
import com.framgia.kotlintraining.moviedb.screen.favorite.FavoriteViewModel
import com.framgia.kotlintraining.moviedb.screen.home.HomeViewModel
import com.framgia.kotlintraining.moviedb.screen.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel<HomeViewModel>()
//    viewModel<FavoriteViewModel>()
//    viewModel<MainViewModel>()
//    viewModel<DetailMovieViewModel>()
//    viewModel<RedditVewModel>()
//    viewModel<MovieViewModel>()

    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { RedditVewModel(get()) }
    viewModel { MovieViewModel(get()) }
}