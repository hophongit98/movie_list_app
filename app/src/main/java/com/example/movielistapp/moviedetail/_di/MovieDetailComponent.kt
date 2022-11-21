package com.example.movielistapp.moviedetail._di

import com.example.movielistapp.moviedetail.view.MovieDetailActivity
import dagger.Subcomponent

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Subcomponent(modules = [MovieDetailModule::class])
interface MovieDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieDetailComponent
    }

    fun inject(movieDetailActivity: MovieDetailActivity)
}