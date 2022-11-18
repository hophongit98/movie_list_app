package com.example.movielistapp.repository

import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieRemoteDataSourceImpl(private val getMovieListUseCase: GetMovieListUseCase) : MovieRemoteDataSource {
    override suspend fun fetchRemoteMovieList(listener: MovieRemoteDataSource.FetchRemoteMovieList) {
        when (val result = getMovieListUseCase.execute(Unit)) {
            is GetMovieListUseCase.Result.Success -> listener.onSuccess(result.movieList)
            is GetMovieListUseCase.Result.Error -> listener.onSuccess(emptyList())
        }
    }
}