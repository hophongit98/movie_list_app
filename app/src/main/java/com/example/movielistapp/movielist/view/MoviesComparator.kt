package com.example.movielistapp.movielist.view

import androidx.recyclerview.widget.DiffUtil
import com.example.movielistapp.movielist.MovieListContract

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MoviesComparator : DiffUtil.ItemCallback<MovieListContract.MovieDisplayableObject>() {

    override fun areItemsTheSame(oldItem: MovieListContract.MovieDisplayableObject, newItem: MovieListContract.MovieDisplayableObject): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MovieListContract.MovieDisplayableObject, newItem: MovieListContract.MovieDisplayableObject): Boolean {
        return oldItem.id == newItem.id
    }
}