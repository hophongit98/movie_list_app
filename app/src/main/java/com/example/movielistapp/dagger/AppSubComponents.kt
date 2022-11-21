package com.example.movielistapp.dagger

import com.example.movielistapp.moviedetail._di.MovieDetailComponent
import com.example.movielistapp.movielist._di.MovieListComponent
import dagger.Module

/**
 * Created by Phillip Truong
 * date 21/11/2022.
 */
@Module(subcomponents = [MovieListComponent::class, MovieDetailComponent::class])
class AppSubComponents