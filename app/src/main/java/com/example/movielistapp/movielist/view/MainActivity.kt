package com.example.movielistapp.movielist.view

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMainBinding
import com.example.movielistapp.moviedetails.MovieDetailActivity
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.viewmodel.MovieListViewModel

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MovieListContract.ViewModel
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun initialise() {

        viewModel = ViewModelProvider(this, MovieListViewModel.Factory)[MovieListViewModel::class.java]

        binding.rvMovieList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            val movieListAdapter = MovieListAdapter {
                viewModel.onItemSelected(it)
            }
            adapter = movieListAdapter
        }

        binding.tvSort.setOnClickListener {
            // handle event sort later
        }
    }

    override fun observeViewModel() {
        if (viewModel is MovieListViewModel) {
            with(viewModel) {
                isLoading.observe(this@MainActivity) {

                }

                navigateToMovieDetail.observe(this@MainActivity) {
                    MovieDetailActivity.start(this@MainActivity, it)
                }

                getMovieList().observe(this@MainActivity, ::handleShowData)
            }
        }
    }

    private fun handleShowData(movie: List<MovieListContract.MovieDisplayableObject>) {
        (binding.rvMovieList.adapter as MovieListAdapter).submitList(movie.toMutableList())
    }
}