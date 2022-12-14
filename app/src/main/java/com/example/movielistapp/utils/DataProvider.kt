package com.example.movielistapp.utils

import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.model.Genre
import com.example.movielistapp.model.Movie

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
object DataProvider {

    const val SHORT_DESCRIPTION = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time"

    fun dummyData(): List<Movie> {
        return listOf(
            mockMovie(
                id = "1", imageUrl ="tenet", movieName = "Tenet",
                duration = 4800, genre = listOf(Genre.ACTION, Genre.SCIFI),
                point = 7.8f, releasedTime = 1599091200,
                shortDescription = SHORT_DESCRIPTION
            ),
            mockMovie(
                id = "2", imageUrl = "spider_man", movieName = "Spider-Man: Into the Spider-Verse",
                duration = 7020, genre = listOf(Genre.ACTION, Genre.ANIMATION, Genre.ADVENTURE),
                point = 8.0f, releasedTime = 1538352000,
                shortDescription = SHORT_DESCRIPTION
            ),
            mockMovie(
                id = "3", imageUrl = "knives_out", movieName = "Knives Out",
                duration = 7800, genre = listOf(Genre.COMEDY, Genre.CRIME, Genre.DRAMA),
                point = 7.0f, releasedTime = 1546300800,
                shortDescription = SHORT_DESCRIPTION
            ),
            mockMovie(
                id = "4", imageUrl = "guardians_of_the_galaxy", movieName = "Guardians of the Galaxy",
                duration = 7260, genre = listOf(Genre.ACTION, Genre.ADVENTURE, Genre.COMEDY),
                point = 8.5f, releasedTime = 1394582400,
                shortDescription = SHORT_DESCRIPTION
            ),
            mockMovie(
                id = "5", imageUrl = "avengers", movieName = "Avengers: Age Of Ultron",
                duration = 8460, genre = listOf(Genre.SCIFI, Genre.ACTION, Genre.ADVENTURE),
                point = 8.5f, releasedTime = 1431388800,
                shortDescription = SHORT_DESCRIPTION
            )
        )
    }

    fun getImage(imageName: String?): Int {
        val context = MovieListApplication.instance
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    private fun mockMovie(
        id: String,
        imageUrl: String,
        movieName: String,
        duration: Int,
        genre: List<Genre>,
        point: Float,
        releasedTime: Int,
        shortDescription: String
    ) = Movie(id, imageUrl, movieName, shortDescription, duration, genre, point, releasedTime, false)
}