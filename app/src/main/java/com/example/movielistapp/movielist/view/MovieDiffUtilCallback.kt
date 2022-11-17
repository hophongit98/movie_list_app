package com.example.movielistapp.movielist.view

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Phillip Truong
 * date 16/11/2022.
 */
class MovieDiffUtilCallback(private val newList: Array<Int>, private val oldList: Array<Int>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}