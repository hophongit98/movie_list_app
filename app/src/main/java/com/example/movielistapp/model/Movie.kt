package com.example.movielistapp.model

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class Movie(
    val id: String,
    val imageUrl: String,
    val movieName: String,
    val duration: Int,
    val kind: List<MovieType>,
    val point: Float,
    val isOnWatchList: Boolean
)

enum class MovieType(type: String) {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    SCIFI("Sci-Fi")
}