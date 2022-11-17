package com.example.movielistapp.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieListDisplayableObject


/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieListViewModel : MovieListContract.ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private var _displayMovieList = MutableLiveData<List<MovieListDisplayableObject>>()
    override val displayMovieList: LiveData<List<MovieListDisplayableObject>> = _displayMovieList

    override fun fetchMoviesList() {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(item: MovieListDisplayableObject) {
        TODO("Not yet implemented")
    }
}