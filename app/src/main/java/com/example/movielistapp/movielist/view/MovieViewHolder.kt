package com.example.movielistapp.movielist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.databinding.ItemMovieBinding
import com.example.movielistapp.movielist.MovieListContract.MovieDisplayableObject

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieViewHolder(private val binding: ItemMovieBinding, private val itemSelected: (MovieDisplayableObject) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun build(item: MovieDisplayableObject) {
        with(binding) {
            tvMoveName.text = item.movieName
            tvDescription.text = item.shortDescription
            tvOnMyWatchList.visibility = if (item.isOnWatchList) View.VISIBLE else View.GONE
        }
        itemView.setOnClickListener { itemSelected.invoke(item) }
    }
}