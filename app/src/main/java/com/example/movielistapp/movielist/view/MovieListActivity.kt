package com.example.movielistapp.movielist.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.MovieListApplication
import com.example.movielistapp.R
import com.example.movielistapp.base.BaseActivity
import com.example.movielistapp.databinding.ActivityMovieListBinding
import com.example.movielistapp.moviedetail.view.MovieDetailActivity
import com.example.movielistapp.movielist.MovieListContract
import com.example.movielistapp.movielist.viewmodel.MovieListViewModel
import javax.inject.Inject

class MovieListActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MovieListContract.ViewModel

    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieListApplication).appComponent.registerMovieListComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun initialise() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)

        binding.rvMovieList.apply {
            layoutManager = LinearLayoutManager(this@MovieListActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MovieListActivity, RecyclerView.VERTICAL))
            setHasFixedSize(true)
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

    private fun handleShowData(movieItems: List<MovieListContract.MovieItemDisplayableObject>?) {
        if (movieItems == null) return
        (binding.rvMovieList).run {
            val movieListAdapter = MovieListAdapter {
                viewModel.onItemSelected(it)
            }.apply { setData(movieItems) }
            adapter = movieListAdapter
        }
    }
}