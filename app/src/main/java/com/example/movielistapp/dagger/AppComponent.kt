package com.example.movielistapp.dagger

import android.content.Context
import com.example.movielistapp.moviedetail._di.MovieDetailComponent
import com.example.movielistapp.moviedetail.view.MovieDetailActivity
import com.example.movielistapp.movielist._di.MovieListComponent
import com.example.movielistapp.movielist.view.MovieListActivity
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Component(modules = [AppModule::class, AppSubComponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun registerMovieListComponent(): MovieListComponent.Factory

    fun registerMovieDetailComponent(): MovieDetailComponent.Factory
}