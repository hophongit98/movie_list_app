package com.example.movielistapp.movielist._di

import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.viewmodel.MovieListViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Module
class MovieListModule {
    @Provides
    fun provideMovieListViewModel(viewModel: MovieListViewModel): MovieListContract.ViewModel = viewModel

}