package com.example.movielistapp.utils

import com.example.movielistapp.model.Genre
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Phillip Truong
 * date 18/11/2022.
 */
class StringUtilsTest {

    @Test
    fun givenDurationGreaterThan60Mins_whenConvertToHourAndMinutes_thenReturnResultWithFormatHourMin() {
        // given
        val duration = 7270

        // when
        val result = StringUtils.convertToHourAndMinutes(duration)

        // then
        assertEquals("2h1min", result)
    }

    @Test
    fun givenDurationLessThan60Mins_whenConvertToHourAndMinutes_thenReturnResultWithFormatMinOnly() {
        // given
        val duration = 3500

        // when
        val result = StringUtils.convertToHourAndMinutes(duration)

        // then
        assertEquals("58min", result)
    }

    @Test
    fun givenOneMovieType_whenConvertMovieTypesToString_thenReturnResultWithOnlyOneType() {
        // given
        val movieType = Genre.ACTION

        // when
        val result = StringUtils.convertMovieTypesToString(listOf(movieType))

        // then
        assertEquals("Action", result)
    }

    @Test
    fun givenMovieTypeList_whenConvertMovieTypesToString_thenReturnResultWithListType() {
        // given
        val movieTypes = listOf(Genre.ACTION, Genre.ADVENTURE)

        // when
        val result = StringUtils.convertMovieTypesToString(movieTypes)

        // then
        assertEquals("Action,Adventure", result)
    }
}