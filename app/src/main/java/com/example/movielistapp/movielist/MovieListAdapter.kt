package com.example.movielistapp.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.databinding.ItemMovieBinding

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieListAdapter(private val listener: MovieListContract.onMovieItemClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = arrayListOf<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (itemCount > position && position != RecyclerView.NO_POSITION) {
            holder.build()
        }
    }

    override fun getItemCount() = movieList.size

    fun setData() {
        // set data here
    }

}