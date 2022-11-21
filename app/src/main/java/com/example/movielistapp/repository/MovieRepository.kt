package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
interface MovieRepository {
    fun getLocalMovieList(): LiveData<List<Movie>>
    suspend fun fetchRemoteMovieList()
    suspend fun insertMovieList(movies: List<Movie>)
    suspend fun getMovieDetail(id: String): Movie
    suspend fun updateIsOnWatchList(isOnWatchList: Boolean, id: String)
}