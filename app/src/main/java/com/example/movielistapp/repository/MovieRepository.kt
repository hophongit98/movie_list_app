package com.example.movielistapp.repository

import com.example.movielistapp.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun getLocalMovieList(): Flow<List<Movie>> {
        return movieLocalDataSource.getMovieList()
    }

    suspend fun fetchRemoteMovieList() {
        movieRemoteDataSource.fetchRemoteMovieList(object : MovieRemoteDataSource.FetchRemoteMovieList {
            override fun onSuccess(movies: List<Movie>) {
                CoroutineScope(Dispatchers.IO).launch {
                    insertMovieList(movies)
                }
            }
        })

    }

    suspend fun insertMovieList(movies: List<Movie>) {
        movieLocalDataSource.insertMovieList(movies)
    }
}