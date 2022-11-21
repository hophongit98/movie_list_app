package com.example.movielistapp.movielist._di

import com.example.movielistapp.movielist.view.MovieListActivity
import dagger.Subcomponent

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Subcomponent(modules = [MovieListModule::class])
interface MovieListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieListComponent
    }

    fun inject(movieListActivity: MovieListActivity)
}