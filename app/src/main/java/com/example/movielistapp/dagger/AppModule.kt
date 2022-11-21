package com.example.movielistapp.dagger

import android.content.Context
import com.example.movielistapp.model.roomdatabase.MovieDao
import com.example.movielistapp.model.roomdatabase.MovieRoomDatabase
import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.moviedetail.viewmodel.MovieDetailViewModel
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.viewmodel.MovieListViewModel
import com.example.movielistapp.repository.*
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Module
class AppModule {

    @Provides
    fun provideUserRepository(repository: MovieRepositoryImpl): MovieRepository = repository

    @Provides
    fun provideMovieLocalDataSource(local: MovieLocalDataSourceImpl): MovieLocalDataSource = local

    @Provides
    fun provideMovieRemoteDataSource(remote: MovieRemoteDataSourceImpl): MovieRemoteDataSource = remote

    @Provides
    fun provideGetMovieListUseCase(useCase: GetMovieListUseCaseImpl): GetMovieListUseCase = useCase

    @Provides
    fun provideDatabase(context: Context): MovieRoomDatabase = MovieRoomDatabase.getDatabase(context)

    @Provides
    fun provideMovieData(roomDatabase: MovieRoomDatabase): MovieDao = roomDatabase.movieDao()

}