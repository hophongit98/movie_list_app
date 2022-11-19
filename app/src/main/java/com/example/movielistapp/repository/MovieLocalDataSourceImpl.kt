package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.roomdatabase.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override fun getMovieList(): LiveData<List<Movie>> {
        return movieDao.getMovieList()
    }

    override suspend fun insertMovieList(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovieList(movies)
        }
    }
}