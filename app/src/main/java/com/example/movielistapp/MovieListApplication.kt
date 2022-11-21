package com.example.movielistapp

import android.app.Application
import com.example.movielistapp.dagger.AppComponent
import com.example.movielistapp.dagger.DaggerAppComponent
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

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {
        lateinit var instance: MovieListApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}