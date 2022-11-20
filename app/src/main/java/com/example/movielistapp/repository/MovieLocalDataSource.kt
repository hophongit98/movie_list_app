package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
interface MovieLocalDataSource {
    fun getMovieList() : LiveData<List<Movie>>
    suspend fun insertMovieList(movies: List<Movie>)
    suspend fun getMovieDetail(id: String): Movie
}