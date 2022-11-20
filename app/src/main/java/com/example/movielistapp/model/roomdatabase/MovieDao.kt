package com.example.movielistapp.model.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.movielistapp.model.Movie

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun getMovieById(id: String): Movie

    @Query("UPDATE Movie SET isOnWatchList=:isOnWatchList WHERE id=:id")
    suspend fun updateIsOnWatchList(isOnWatchList: Boolean, id: String)

    @Insert(onConflict = IGNORE)
    suspend fun insertMovieList(movies: List<Movie>)
}