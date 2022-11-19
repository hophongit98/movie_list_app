package com.example.movielistapp.movielist.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieListAdapter(private val itemSelected: (MovieDisplayableObject) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = arrayListOf<MovieDisplayableObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, itemSelected)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.build(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun setData(newMovies: List<MovieDisplayableObject>) {
        val diffResult = DiffUtil.calculateDiff(MoviesComparator(newMovies, movieList))
        diffResult.dispatchUpdatesTo(this)

        with(movieList) {
            clear()
            addAll(newMovies)
        }
    }
}