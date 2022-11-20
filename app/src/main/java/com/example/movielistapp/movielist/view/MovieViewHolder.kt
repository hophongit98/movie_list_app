package com.example.movielistapp.movielist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.databinding.ItemMovieBinding
import com.example.movielistapp.movielist.MovieListContract.MovieItemDisplayableObject

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieViewHolder(private val binding: ItemMovieBinding, private val itemSelected: (MovieItemDisplayableObject) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun build(item: MovieItemDisplayableObject) {
        with(binding) {
            tvMoveName.text = item.movieName
            tvDescription.text = item.shortDescription
            tvOnMyWatchList.visibility = if (item.isOnWatchList) View.VISIBLE else View.GONE
        }
        itemView.setOnClickListener { itemSelected.invoke(item) }
    }

    companion object {
        fun create(parent: ViewGroup, itemSelected: (MovieItemDisplayableObject) -> Unit): MovieViewHolder {
            return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemSelected)
        }
    }
}