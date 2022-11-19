package com.example.movielistapp.usecase.getmovielistusecase

import com.example.movielistapp.utils.DataProvider

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class GetMovieListUseCaseImpl : GetMovieListUseCase {
    override suspend fun execute(input: Unit): GetMovieListUseCase.Result {
        // TO-DO: call api get data from server here
        return GetMovieListUseCase.Result.Success(DataProvider.dummyData())
    }
}