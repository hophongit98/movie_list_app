package com.example.movielistapp.repository

import com.example.movielistapp.model.Movie
import javax.inject.Singleton

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
@Singleton
interface MovieRemoteDataSource {
    suspend fun fetchRemoteMovieList(listener: FetchRemoteMovieList)

    interface FetchRemoteMovieList {
        fun onSuccess(movies: List<Movie>)
    }
}