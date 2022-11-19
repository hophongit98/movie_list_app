package com.example.movielistapp.utils

import com.example.movielistapp.model.Genre

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
object StringUtils {

    fun convertToHourAndMinutes(seconds: Int): String {
        var result = ""
        val hour = seconds / 3600
        if (hour > 0) result += "${hour}h"
        val mins = (seconds - hour * 3600) / 60
        return result + if (mins > 0) "${mins}min" else ""
    }

    fun convertMovieTypesToString(genres: List<Genre>): String {
        var str = ""
        genres.forEachIndexed { index, item ->
            str += item.type
            if (index != genres.size - 1) str += ","
        }
        return str
    }
}