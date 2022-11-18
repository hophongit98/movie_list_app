package com.example.movielistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
@Entity
class Movie(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val movieName: String,
    val duration: Int,
    val kind: List<MovieType>,
    val point: Float,
    val isOnWatchList: Boolean
)

enum class MovieType(val type: String) {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    SCIFI("Sci-Fi")
}