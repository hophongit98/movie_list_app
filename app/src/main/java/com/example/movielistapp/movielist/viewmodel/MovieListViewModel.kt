package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.MovieType
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject
import com.example.movielistapp.repository.MovieRepository
import com.example.movielistapp.utils.StringUtils
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieListViewModel(private val movieRepository: MovieRepository) : MovieListContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _navigateToMovieDetail = MutableLiveData<String>()
    override val navigateToMovieDetail: LiveData<String> = _navigateToMovieDetail

    private var _movieListLiveData = MutableLiveData<List<Movie>>()

    private var _displayMovie: LiveData<List<MovieDisplayableObject>> = Transformations.map(_movieListLiveData) {
        transformMovieListToDisplayableObjects(it)
    }
    override val displayMovie: LiveData<List<MovieDisplayableObject>> = _displayMovie

    override fun getMoviesList() {
        val movies = movieRepository.getLocalMovieList().asLiveData().value
        if (movies.isNullOrEmpty()) {
            viewModelScope.launch {
                movieRepository.fetchRemoteMovieList()
            }
        } else {
            _movieListLiveData.value = movies
        }
    }

    override fun onItemSelected(item: MovieDisplayableObject) {
        _navigateToMovieDetail.value = item.id
    }

    private fun transformMovieListToDisplayableObjects(movies: List<Movie>): List<MovieDisplayableObject> {
        return movies.map {
            MovieDisplayableObject(
                it.id, it.imageUrl, it.movieName, formatToShortDescription(it.duration, it.kind), it.isOnWatchList
            )
        }
    }

    private fun formatToShortDescription(duration: Int, types: List<MovieType>): String {
        return "${StringUtils.convertToHourAndMinutes(duration)}-${StringUtils.convertMovieTypesToString(types)}"
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MovieListApplication).repository
                MovieListViewModel(myRepository)
            }
        }
    }
}