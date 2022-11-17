package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieListDisplayableObject
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCase
import com.example.movielistapp.usecase.getmovielistusecase.GetMovieListUseCaseImpl
import kotlinx.coroutines.launch


/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieListViewModel : MovieListContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _displayMovieList = MutableLiveData<List<MovieListDisplayableObject>>()
    override val displayMovieList: LiveData<List<MovieListDisplayableObject>> = _displayMovieList

    private var _navigateToMovieDetail = MutableLiveData<String>()
    override val navigateToMovieDetail: LiveData<String> = _navigateToMovieDetail

    override fun fetchMoviesList() {

    }

    override fun onItemSelected(item: MovieListDisplayableObject) {
        _navigateToMovieDetail.value = item.id
    }
}