package com.example.movielistapp.movielist.view

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMovieListBinding
import com.example.movielistapp.moviedetail.MovieDetailActivity
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.viewmodel.MovieListViewModel

class MovieListActivity : BaseActivity() {

    private lateinit var viewModel: MovieListContract.ViewModel
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMovieListBinding>(this, R.layout.activity_movie_list)
    }

    override fun initialise() {

        viewModel = ViewModelProvider(this, MovieListViewModel.Factory)[MovieListViewModel::class.java]

        binding.rvMovieList.apply {
            layoutManager = LinearLayoutManager(this@MovieListActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

        binding.tvSort.setOnClickListener {
            // handle event sort later
        }
    }

    override fun observeViewModel() {
        if (viewModel is MovieListViewModel) {
            with(viewModel) {
                isLoading.observe(this@MovieListActivity) {

                }

                navigateToMovieDetail.observe(this@MovieListActivity) {
                    MovieDetailActivity.start(this@MovieListActivity, it)
                }

                getMovieList().observe(this@MovieListActivity, ::handleShowData)
            }
        }
    }

    private fun handleShowData(movies: List<MovieListContract.MovieDisplayableObject>) {
        (binding.rvMovieList).run {
            val movieListAdapter = MovieListAdapter {
                viewModel.onItemSelected(it)
            }.apply { setData(movies) }
            adapter = movieListAdapter
        }
    }
}