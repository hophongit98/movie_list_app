package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.MovieType
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase
import com.example.movielistapp.utils.StringUtils
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieListViewModel(private val getMovieListUseCase : GetMovieListUseCase) : MovieListContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _navigateToMovieDetail = MutableLiveData<String>()
    override val navigateToMovieDetail: LiveData<String> = _navigateToMovieDetail

    private var _movieListLiveData = MutableLiveData<List<Movie>>()

    private var _displayMovie: LiveData<List<MovieDisplayableObject>> = Transformations.map(_movieListLiveData) {
        transformMovieListToDisplayableObjects(it)
    }
    override val displayMovie: LiveData<List<MovieDisplayableObject>> = _displayMovie

    override fun fetchMoviesList() {
        viewModelScope.launch {
            when (val result = getMovieListUseCase.execute(Unit)) {
                is GetMovieListUseCase.Result.Success -> handleGetMovieListSuccess(result)
                is GetMovieListUseCase.Result.Error -> handleGetMovieListError(result)
            }
        }
    }

    override fun onItemSelected(item: MovieDisplayableObject) {
        _navigateToMovieDetail.value = item.id
    }

    private fun handleGetMovieListSuccess(success: GetMovieListUseCase.Result.Success) {
        _movieListLiveData.value = success.movieList
    }

    private fun handleGetMovieListError(error: GetMovieListUseCase.Result.Error) {

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
}