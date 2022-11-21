package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
@Singleton
class MovieRepositoryImpl @Inject constructor(private val movieLocalDataSource: MovieLocalDataSource, private val movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository {

    override fun getLocalMovieList(): LiveData<List<Movie>> {
        return movieLocalDataSource.getMovieList()
    }

    override suspend fun fetchRemoteMovieList() {
        movieRemoteDataSource.fetchRemoteMovieList(object : MovieRemoteDataSource.FetchRemoteMovieList {
            override fun onSuccess(movies: List<Movie>) {
                CoroutineScope(Dispatchers.IO).launch {
                    insertMovieList(movies)
                }
            }
        })

    }

    override suspend fun insertMovieList(movies: List<Movie>) {
        movieLocalDataSource.insertMovieList(movies)
    }

    override suspend fun getMovieDetail(id: String): Movie {
        return movieLocalDataSource.getMovieDetail(id)
    }

    override suspend fun updateIsOnWatchList(isOnWatchList: Boolean, id: String) {
        return movieLocalDataSource.updateIsOnWatchList(isOnWatchList, id)
    }
}