package com.example.movielistapp.repository

import com.example.movielistapp.model.Movie

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
interface MovieRemoteDataSource {
    suspend fun fetchRemoteMovieList(listener: FetchRemoteMovieList)

    interface FetchRemoteMovieList {
        fun onSuccess(movies: List<Movie>)
    }
}