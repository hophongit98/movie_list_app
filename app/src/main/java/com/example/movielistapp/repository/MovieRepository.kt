package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    fun getLocalMovieList(): LiveData<List<Movie>> {
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

    suspend fun getMovieDetail(id: String): Movie {
        TODO("Not yet implemented")
    }
}