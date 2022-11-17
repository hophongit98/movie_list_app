package com.example.movielistapp.usecase.getmovielistusecase

import com.example.movielistapp.model.Movie
import com.example.movielistapp.usecase.UseCase

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
interface GetMovieListUseCase : UseCase<Unit, GetMovieListUseCase.Result> {

    sealed class Result {
        class Success(val movieList: List<Movie>): Result()
        object Error: Result()
    }
}