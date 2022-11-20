package com.example.movielistapp.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.model.Movie
import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.repository.MovieRepository
import kotlinx.coroutines.launch

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class MovieDetailViewModel(private val repository: MovieRepository) : MovieDetailContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _movieDetail = MutableLiveData<MovieDetailContract.MovieDetailDisplayableObject>()
    override val movieDetail: LiveData<MovieDetailContract.MovieDetailDisplayableObject> = _movieDetail

    private var _navigateToMovieList = MutableLiveData<Unit>()
    override val navigateToMovieList: LiveData<Unit> = _navigateToMovieList

    override fun getMovieDetail(id: String) {
        viewModelScope.launch {
            val movieDo = mapToMovieDetailDisplayableObject(repository.getMovieDetail(id))
            _movieDetail.value = movieDo
        }
    }

    override fun onAddToWatchList() {
        TODO("Not yet implemented")
    }

    override fun onRemoveFromWatchList() {
        TODO("Not yet implemented")
    }

    private fun mapToMovieDetailDisplayableObject(movie: Movie): MovieDetailContract.MovieDetailDisplayableObject {
        return MovieDetailContract.MovieDetailDisplayableObject(
            imageUrl = movie.imageUrl,
            movieName = movie.movieName,
            point = movie.point,
            shortDescription = movie.shortDescription,
            genre = movie.genre,
            releasedDate = movie.releaseTime,
            isOnWatchList = movie.isOnWatchList
        )
    }
}