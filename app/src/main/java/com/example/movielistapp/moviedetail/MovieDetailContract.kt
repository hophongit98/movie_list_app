package com.example.movielistapp.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movielistapp.model.Genre

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
interface MovieDetailContract {
    interface Navigation {
        val navigateToMovieList: LiveData<Unit>
    }

    abstract class ViewModel : androidx.lifecycle.ViewModel(), Navigation {
        abstract val isLoading: LiveData<Boolean>
        abstract val movieDetail: LiveData<MovieDetailDisplayableObject>

        abstract fun getMovieDetail(id: String)
        abstract fun onAddToWatchList()
        abstract fun onRemoveFromWatchList()
    }

    data class MovieDetailDisplayableObject(
        val imageUrl: String,
        val movieName: String,
        val point: Float,
        val shortDescription: String,
        val genre: List<Genre>,
        val releasedDate: Int,
        val isOnWatchList: Boolean
    )
}