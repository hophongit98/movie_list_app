package com.example.movielistapp.model

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
    val shortDescription: String,
    val duration: Int,
    val kind: List<Genre>,
    val point: Float,
    val releaseTime: Int,
    val isOnWatchList: Boolean
)

enum class Genre(val type: String) {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    SCIFI("Sci-Fi")
}