package com.example.movielistapp.moviedetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMovieDetailBinding
import com.example.movielistapp.moviedetail.MovieDetailContract
import com.example.movielistapp.moviedetail.viewmodel.MovieDetailViewModel
import com.example.movielistapp.utils.StringUtils
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 17/11/2022.
 */
class MovieDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MovieDetailContract.ViewModel

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieListApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            it.title = getString(R.string.movie_list_movies)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun initialise() {
        super.initialise()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        intent.getStringExtra(MOVIE_ID)?.let { viewModel.getMovieDetail(it) }

        binding.btnAddToWatchList.setOnClickListener {
            viewModel.onAddToWatchList()
            updateButtonsVisible(btnAttClicked = true)
        }

        binding.btnRemoveFromWatchList.setOnClickListener {
            viewModel.onRemoveFromWatchList()
            updateButtonsVisible(btnAttClicked = false)
        }
    }

    override fun observeViewModel() {
        if (viewModel is MovieDetailViewModel) {
            viewModel.movieDetail.observe(this) { movie ->
                updateUI(movie)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun updateButtonsVisible(btnAttClicked: Boolean) {
        binding.btnAddToWatchList.isVisible = !btnAttClicked
        binding.btnRemoveFromWatchList.isVisible = btnAttClicked
    }

    private fun updateUI(movie: MovieDetailContract.MovieDetailDisplayableObject) {
        with(binding) {
            tvMovieName.text = movie.movieName
            tvPoint.text = movie.point.toString()
            tvShortDescription.text = movie.shortDescription
            tvGenre.text = StringUtils.convertMovieTypesToString(movie.genre)
            tvReleasedDate.text = StringUtils.formatStringYYYYDMMM(movie.releasedDate)
            if (movie.isOnWatchList) {
                btnAddToWatchList.isVisible = false
                btnRemoveFromWatchList.isVisible = true
            } else {
                btnAddToWatchList.isVisible = true
                btnRemoveFromWatchList.isVisible = false
            }
            Glide.with(this@MovieDetailActivity)
                .load(resources.getIdentifier(movie.imageUrl, "drawable", this@MovieDetailActivity.packageName))
                .placeholder(R.drawable.ic_priority_high)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(ivImage)
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