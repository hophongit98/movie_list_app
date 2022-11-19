package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.Genre
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject
import com.example.movielistapp.repository.MovieRepository
import com.example.movielistapp.utils.StringUtils
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieListViewModel(private val movieRepository: MovieRepository) :
    MovieListContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _navigateToMovieDetail = MutableLiveData<String>()
    override val navigateToMovieDetail: LiveData<String> = _navigateToMovieDetail

    private var _movieListLiveData = MutableLiveData<List<Movie>>()

    override fun onItemSelected(item: MovieDisplayableObject) {
        _navigateToMovieDetail.value = item.id
    }

    override fun getMovieList(): LiveData<List<MovieDisplayableObject>> {
        return Transformations.map(movieRepository.getLocalMovieList()) { movies ->
            if (movies.isNullOrEmpty()) {
                viewModelScope.launch {
                    movieRepository.fetchRemoteMovieList()
                }
                null
            } else {
                transformMovieListToDisplayableObjects(movies)
            }
        }
    }

    private fun transformMovieListToDisplayableObjects(movies: List<Movie>): List<MovieDisplayableObject> {
        return movies.map {
            MovieDisplayableObject(
                it.id,
                it.imageUrl,
                it.movieName,
                formatToShortDescription(it.duration, it.genre),
                it.isOnWatchList
            )
        }
    }

    private fun formatToShortDescription(duration: Int, types: List<Genre>): String {
        return "${StringUtils.convertToHourAndMinutes(duration)}-${
            StringUtils.convertMovieTypesToString(
                types
            )
        }"
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return MovieListViewModel(MovieListApplication.instance.repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}