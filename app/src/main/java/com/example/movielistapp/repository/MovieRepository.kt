package com.example.movielistapp.repository

import com.example.movielistapp.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieRepository(private val movieLocalDataSource: MovieLocalDataSource) {

    fun getLocalMovieList(): Flow<List<Movie>> {
        return movieLocalDataSource.getMovieList()
    }

    suspend fun fetchRemoteMovieList() {

    }

    suspend fun insertMovieList(movies: List<Movie>) {
        movieLocalDataSource.insertMovieList(movies)
    }
}