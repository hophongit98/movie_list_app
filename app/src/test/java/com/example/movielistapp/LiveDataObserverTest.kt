package com.example.movielistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Phillip Truong
 * date 20/11/2022.
 */
class LiveDataObserverTest<T>(private val event: LiveData<T>) : Observer<T> {

    val events = mutableListOf<T>()

    init {
        event.observeForever(this)
    }

    override fun onChanged(t: T) {
        events.add(t)
    }
}