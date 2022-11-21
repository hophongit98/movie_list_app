package com.example.movielistapp.repository

import androidx.lifecycle.LiveData
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.roomdatabase.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : MovieLocalDataSource {

    override fun getMovieList(): LiveData<List<Movie>> {
        return movieDao.getMovieList()
    }

    override suspend fun insertMovieList(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovieList(movies)
        }
    }

    override suspend fun getMovieDetail(id: String): Movie {
        return withContext(Dispatchers.IO) {
            movieDao.getMovieById(id)
        }
    }

    override suspend fun updateIsOnWatchList(isOnWatchList: Boolean, id: String) {
        return withContext(Dispatchers.IO) {
            movieDao.updateIsOnWatchList(isOnWatchList, id)
        }
    }
}