package com.example.movielistapp.usecase

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
interface UseCase<Input, Output> {
    suspend fun execute(input: Input): Output
}