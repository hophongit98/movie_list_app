package com.example.movielistapp.moviedetail.view

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMovieDetailBinding
import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.moviedetail.viewmodel.MovieDetailViewModel
import com.example.movielistapp.utils.StringUtils

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieDetailActivity : BaseActivity() {

    private lateinit var viewModel: MovieDetailContract.ViewModel

    private lateinit var binding: ActivityMovieDetailBinding

    override fun initialise() {
        super.initialise()
        viewModel = ViewModelProvider(this, MovieDetailViewModel.Factory)[MovieDetailViewModel::class.java].also {  viewModel ->
            intent.getStringExtra(MOVIE_ID)?.let { viewModel.getMovieDetail(it) }
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
    }

    override fun observeViewModel() {
        if (viewModel is MovieDetailViewModel) {
            viewModel.movieDetail.observe(this) { movie ->
                updateUI(movie)
            }
        }
    }

    private fun updateUI(movie: MovieDetailContract.MovieDetailDisplayableObject) {
        with(binding) {
            tvMovieName.text = movie.movieName
            tvPoint.text = movie.point.toString()
            tvShortDescription.text = movie.shortDescription
            tvGenre.text = StringUtils.convertMovieTypesToString(movie.genre)
            tvReleasedDate.text = StringUtils.convertToHourAndMinutes(movie.releasedDate)
            btnAddToWatchList.text = if (!movie.isOnWatchList) getString(R.string.movie_detail_add_to_watchlist) else getString(R.string.movie_detail_remove_from_watchlist)
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun start(context: Context, moveId: String) {
            val intent = Intent(context, MovieDetailActivity::class.java).putExtra(MOVIE_ID, moveId)
            context.startActivity(intent)
        }
    }
}