package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.movielistapp.LiveDataObserverTest
import com.example.movielistapp.ViewModelTestBase
import com.example.movielistapp.model.Genre
import com.example.movielistapp.model.Movie
import com.example.movielistapp.movielist.MovieListContract
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieListViewModelTest : ViewModelTestBase() {

    lateinit var viewModel: MovieListViewModel

    @Mock
    lateinit var repository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieListViewModel(repository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenMovieItem_whenMovieItemSelected_thenNavigateToMovieDetail() {
        // given
        val item = MovieListContract.MovieItemDisplayableObject("123", "url", "name", "des", false)
        val navigateObserver = LiveDataObserverTest(viewModel.navigateToMovieDetail)

        // when
        viewModel.onItemSelected(item)

        // then
        assertEquals(1, navigateObserver.events.size)
        assertEquals("123", navigateObserver.events.first())
    }

    @Test
    fun givenGetLocalMovieListNotEmpty_whenGetMovieList_thenReceiveDisplayableObject() {
        // given
        val movies = listOf(
            Movie(
                "1", "imageUrl", "Movie Name", "ShortDescription",
                7020, listOf(Genre.ACTION), 8.0f, 1599091200, false
            )
        )
        whenever(repository.getLocalMovieList()).thenReturn(MutableLiveData(movies))
        val navigateObserver = LiveDataObserverTest(viewModel.getMovieList())

        // when
        viewModel.getMovieList()

        // then
        assertEquals(1, navigateObserver.events.size)
        val movie = navigateObserver.events.first()[0]
        assert(movie is MovieListContract.MovieItemDisplayableObject)
        with(movie) {
            assertEquals("1", id)
            assertEquals("imageUrl", imageUrl)
            assertEquals("Movie Name (2020)", movieName)
            assertEquals("1h57min - Action", shortDescription)
            assertFalse(isOnWatchList)
        }
    }

    @Test
    fun givenGetLocalMovieListEmpty_whenGetMovieList_thenFetchMovieListFromRemote() {
        // given
        val movies = emptyList<Movie>()
        whenever(repository.getLocalMovieList()).thenReturn(MutableLiveData(movies))
        val navigateObserver = LiveDataObserverTest(viewModel.getMovieList())

        // when
        viewModel.getMovieList()

        // then
        runBlocking {
            verify(repository).fetchRemoteMovieList()
        }

    }
}