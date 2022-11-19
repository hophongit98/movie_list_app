package com.example.movielistapp.model.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.movielistapp.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun getMovieById(id: String): Flow<Movie>

    @Insert(onConflict = IGNORE)
    suspend fun insertMovieList(movies: List<Movie>)
}