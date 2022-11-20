package com.example.movielistapp.moviedetail.viewmodel

import com.example.movielistapp.LiveDataObserverTest
import com.example.movielistapp.ViewModelTestBase
import com.example.movielistapp.model.Genre
import com.example.movielistapp.model.Movie
import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieDetailViewModelTest : ViewModelTestBase() {

    lateinit var viewModel: MovieDetailViewModel

    @Mock
    lateinit var repository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieDetailViewModel(repository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun giveMovieId_whenGetMovieDetail_returnMovieDisplayableObject(): Unit = runBlocking {
        // given
        val movieId = "123"
        whenever(repository.getMovieDetail(movieId)).thenReturn(
            Movie("1", "imageUrl", "Movie Name", "ShortDescription",
                7020, listOf(Genre.ACTION), 8.0f, 13456789, false)
        )
        val movieDetailObserver = LiveDataObserverTest(viewModel.movieDetail)

        // then
        viewModel.getMovieDetail(movieId)

        // then
        assertEquals(1, movieDetailObserver.events.size)
        val movie = movieDetailObserver.events.first()
        assert(movie is MovieDetailContract.MovieDetailDisplayableObject)
        with(movie) {
            assertEquals("imageUrl", imageUrl)
            assertEquals("Movie Name", movieName)
            assertEquals(8.0f, point)
            assertEquals("ShortDescription", shortDescription)
            assertEquals(1, genre.size)
            assertEquals(Genre.ACTION, genre[0])
            assertEquals(13456789, releasedDate)
            assertFalse(isOnWatchList)
        }
    }

}