package com.example.movielistapp.repository

import com.example.movielistapp.model.Genre
import com.example.movielistapp.model.Movie
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieRemoteDataSourceTest {

    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var getMovieListUseCase: GetMovieListUseCase

    @Mock
    lateinit var listener: MovieRemoteDataSource.FetchRemoteMovieList

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        movieRemoteDataSource = MovieRemoteDataSourceImpl(getMovieListUseCase)
    }

    @Test
    fun givenGetMovieListSuccess_whenFetchRemoteMoveList_thenReturnMovieList(): Unit = runBlocking {
        // given
        val success = GetMovieListUseCase.Result.Success(listOf(
            Movie("123", "imageUrl", "Movie Name", "ShortDescription",
            123456, listOf(Genre.ACTION), 8.0f, 13456789, false)
        ))
        whenever(getMovieListUseCase.execute(Unit)).thenReturn(success)

        // when
        movieRemoteDataSource.fetchRemoteMovieList(listener)

        // then
        val argumentCaptor = argumentCaptor<List<Movie>>()
        verify(listener).onSuccess(argumentCaptor.capture())
        val movies = argumentCaptor.firstValue
        assertEquals(1, movies.size)

        with(movies.first()) {
            assertEquals("123", "123")
            assertEquals("imageUrl", imageUrl)
            assertEquals("Movie Name", movieName)
            assertEquals("ShortDescription", shortDescription)
            assertEquals(123456, duration)
            assertEquals(8.0f, point)
            assertEquals(13456789, releaseTime)
            Assert.assertFalse(isOnWatchList)
            assertEquals(1, genre.size)
            assertEquals(Genre.ACTION, genre.first())
        }
    }
}