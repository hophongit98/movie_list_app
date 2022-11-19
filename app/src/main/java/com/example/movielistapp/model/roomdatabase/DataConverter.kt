package com.example.movielistapp.model.roomdatabase

import androidx.room.TypeConverter
import com.example.movielistapp.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Phillip Truong
 * date 19/11/2022.
 */
class DataConverter {

    @TypeConverter
    fun fromGenreString(value: String?): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String? {
        return Gson().toJson(genres)
    }
}