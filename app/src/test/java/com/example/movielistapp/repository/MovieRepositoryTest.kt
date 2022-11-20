package com.example.movielistapp.repository

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieRepositoryTest {

    lateinit var repository: MovieRepository
    
    @Mock
    lateinit var movieLocalDataSource: MovieLocalDataSource
    
    @Mock
    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MovieRepository(movieLocalDataSource, movieRemoteDataSource)
    }

    @Test
    fun whenGetLocalMovieList_thenMovieLocalDataSourceHandle() {
        // when
        repository.getLocalMovieList()
        
        // then
        verify(movieLocalDataSource).getMovieList()
    }

    @Test
    fun whenInsertMovieList_thenMovieLocalDataSourceHandle(): Unit = runBlocking {
        // when
        repository.insertMovieList(listOf())

        // then
        verify(movieLocalDataSource).insertMovieList(listOf())
    }

    @Test
    fun whenGetMovieDetail_thenMovieLocalDataSourceHandle(): Unit = runBlocking {
        // when
        repository.getMovieDetail("123")

        // then
        verify(movieLocalDataSource).getMovieDetail("123")
    }

    @Test
    fun whenFetchRemoteMovieList_thenMovieRemoteDataSourceHandle(): Unit = runBlocking {
        // when
        repository.fetchRemoteMovieList()

        // then
        verify(movieRemoteDataSource).fetchRemoteMovieList(any())
    }
}