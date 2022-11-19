package com.example.movielistapp.movielist.view

import androidx.recyclerview.widget.DiffUtil
import com.example.movielistapp.movielist.MovieListContract

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MoviesComparator(private val newList: List<MovieListContract.MovieDisplayableObject>, private val oldList: List<MovieListContract.MovieDisplayableObject>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id ==  oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] ==  oldList[oldItemPosition]
    }
}