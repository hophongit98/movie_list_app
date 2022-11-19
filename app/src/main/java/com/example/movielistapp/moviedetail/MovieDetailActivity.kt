package com.example.movielistapp.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMovieDetailBinding

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieDetailActivity : BaseActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMovieDetailBinding>(this, R.layout.activity_movie_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun start(context: Context, moveId: String) {
            val intent = Intent(context, MovieDetailActivity::class.java).putExtra(MOVIE_ID, moveId)
            context.startActivity(intent)
        }
    }
}