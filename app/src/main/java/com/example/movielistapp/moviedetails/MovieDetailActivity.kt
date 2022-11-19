package com.example.movielistapp.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.movielistapp.base.BaseActivity

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun start(context: Context, moveId: String) {
            val intent = Intent().putExtra(MOVIE_ID, moveId)
            context.startActivity(intent)
        }
    }
}