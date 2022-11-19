package com.example.movielistapp.model.roomdatabase

import android.content.Context
import androidx.room.*
import com.example.movielistapp.model.Movie

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieRoomDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            return instance ?: synchronized(this) {
                val ins = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                ).build()
                instance = ins
                ins
            }
        }
    }
}