package com.example.movielistapp.repository

import com.example.movielistapp.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
interface MovieLocalDataSource {
    fun getMovieList() : Flow<List<Movie>>
    suspend fun insertMovieList(movies: List<Movie>)
}