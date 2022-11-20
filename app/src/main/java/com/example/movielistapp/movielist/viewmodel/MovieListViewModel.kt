package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.*
import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.Genre
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieItemDisplayableObject
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

    override fun onItemSelected(item: MovieItemDisplayableObject) {
        _navigateToMovieDetail.value = item.id
    }

    override fun getMovieList(): LiveData<List<MovieItemDisplayableObject>> {
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

    private fun transformMovieListToDisplayableObjects(movies: List<Movie>): List<MovieItemDisplayableObject> {
        return movies.map {
            MovieItemDisplayableObject(
                it.id,
                it.imageUrl,
                formatMovieNameAndReleasedYear(it.movieName, it.releaseTime),
                formatToShortDescription(it.duration, it.genre),
                it.isOnWatchList
            )
        }
    }

    private fun formatMovieNameAndReleasedYear(name: String, releasedTime: Int): String {
        return "$name (${StringUtils.formatStringYYYY(releasedTime)})"
    }

    private fun formatToShortDescription(duration: Int, types: List<Genre>): String {
        return "${StringUtils.convertToHourAndMinutes(duration)} - ${StringUtils.convertMovieTypesToString(types)}"
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