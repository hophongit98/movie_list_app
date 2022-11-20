package com.example.movielistapp.usecase

import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class GetMovieListUseCaseImplTest {

    lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setup() {
        getMovieListUseCase = GetMovieListUseCaseImpl()
    }

    @Test
    fun whenExecuteUseCase_thenReturnMovieListData() {
        // when
        val result = runBlocking { getMovieListUseCase.execute(Unit) }

        // then
        assert(result is GetMovieListUseCase.Result.Success)
        with(result as GetMovieListUseCase.Result.Success)
        {
            assertEquals(5, result.movieList.size)
            val first = result.movieList.first()
            assertEquals("Tenet", first.movieName)
            assertEquals(7.8f, first.point)
            assertEquals(1599091200, first.releaseTime)

            val last = result.movieList.last()
            assertEquals("Adventures: Age Of Ultron", last.movieName)
            assertEquals(8.5f, last.point)
            assertEquals(1431388800, last.releaseTime)
        }
    }
}