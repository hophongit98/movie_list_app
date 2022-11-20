package com.example.movielistapp.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel as BaseViewModel

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
interface MovieListContract {

    interface Navigation {
        val navigateToMovieDetail: LiveData<String>
    }

    abstract class ViewModel : BaseViewModel(), Navigation {
        abstract val isLoading: LiveData<Boolean>

        abstract fun onItemSelected(item: MovieItemDisplayableObject)
        abstract fun getMovieList(): LiveData<List<MovieItemDisplayableObject>>
    }

    data class MovieItemDisplayableObject(
        val id: String,
        val imageUrl: String,
        val movieName: String,
        val shortDescription: String,
        val isOnWatchList: Boolean
    )
}