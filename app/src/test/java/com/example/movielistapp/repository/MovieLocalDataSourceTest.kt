package com.example.movielistapp.repository

import com.example.movielistapp.model.Genre
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.roomdatabase.MovieDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieLocalDataSourceTest {

    lateinit var localDataSource: MovieLocalDataSource

    @Mock
    lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        localDataSource = MovieLocalDataSourceImpl(movieDao)
    }

    @Test
    fun getMovieListTest() {
        localDataSource.getMovieList()
        verify(movieDao).getMovieList()
    }

    @Test
    fun insertMovieListTest(): Unit = runBlocking {
        localDataSource.insertMovieList(listOf())
        verify(movieDao).insertMovieList(listOf())
    }

    @Test
    fun givenMovieId_whenGetMovieById_thenReturnMovieDetail(): Unit = runBlocking {
        // given
        val id = "123"
        whenever(movieDao.getMovieById(id)).thenReturn(
            Movie(id, "imageUrl", "Movie Name", "ShortDescription",
                123456, listOf(Genre.ACTION), 8.0f, 13456789, false)
        )
        // when
        val movie = localDataSource.getMovieDetail(id)

        // then
        verify(movieDao).getMovieById(id)
        assertEquals("123", id)
        assertEquals("imageUrl", movie.imageUrl)
        assertEquals("Movie Name", movie.movieName)
        assertEquals("ShortDescription", movie.shortDescription)
        assertEquals(123456, movie.duration)
        assertEquals(8.0f, movie.point)
        assertEquals(13456789, movie.releaseTime)
        assertFalse(movie.isOnWatchList)
        assertEquals(1, movie.genre.size)
        assertEquals(Genre.ACTION, movie.genre.first())
    }
}