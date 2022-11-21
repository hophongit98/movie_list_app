package com.example.movielistapp.dagger

import android.content.Context
import com.example.movielistapp.moviedetail.view.MovieDetailActivity
import com.example.movielistapp.movielist.view.MovieListActivity
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(movieListActivity: MovieListActivity)

    fun inject(movieDetailActivity: MovieDetailActivity)
}