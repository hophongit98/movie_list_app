package com.example.movielistapp.moviedetail._di

import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.moviedetail.viewmodel.MovieDetailViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Module
class MovieDetailModule {
    @Provides
    fun provideMovieListViewModel(viewModel: MovieDetailViewModel): MovieDetailContract.ViewModel = viewModel

}