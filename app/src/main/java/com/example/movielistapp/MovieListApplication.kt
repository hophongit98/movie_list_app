package com.example.movielistapp

import android.app.Application
import com.example.movielistapp.model.roomdatabase.MovieRoomDatabase
import com.example.movielistapp.repository.MovieLocalDataSourceImpl
import com.example.movielistapp.repository.MovieRemoteDataSourceImpl
import com.example.movielistapp.repository.MovieRepository
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCaseImpl

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieListApplication : Application() {
    val database by lazy { MovieRoomDatabase.getDatabase(this) }
    val repository by lazy { MovieRepository(
        MovieLocalDataSourceImpl(database.movieDao()), MovieRemoteDataSourceImpl(GetMovieListUseCaseImpl())
    ) }

    companion object {
        lateinit var instance: MovieListApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}