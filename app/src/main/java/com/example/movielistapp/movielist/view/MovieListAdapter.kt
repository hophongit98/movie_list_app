package com.example.movielistapp.movielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.databinding.ItemMovieBinding
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieListAdapter(private val itemSelected: (MovieDisplayableObject) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = arrayListOf<MovieDisplayableObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemSelected)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (itemCount > position && position != RecyclerView.NO_POSITION) {
            holder.build(movieList[position])
        }
    }

    override fun getItemCount() = movieList.size

    fun setData(movie: List<MovieListContract.MovieDisplayableObject>) {
        // set data here
    }

}