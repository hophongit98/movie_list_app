package com.example.movielistapp.repository

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override suspend fun fetchRemoteMovieList(listener: MovieRemoteDataSource.FetchRemoteMovieList) {
        listener.onSuccess(listOf())
    }
}