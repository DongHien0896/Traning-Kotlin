package com.framgia.kotlintraining.moviedb.screen.favorite

import androidx.lifecycle.Observer
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.databinding.FragmentRefeshBinding
import com.framgia.kotlintraining.moviedb.screen.detail.DetailMovieFragment
import com.framgia.kotlintraining.moviedb.screen.home.adapter.MoviePopularAdapter
import kotlinx.android.synthetic.main.fragment_refesh.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentRefeshBinding, FavoriteViewModel>() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_refesh

    override val viewModel by viewModel<FavoriteViewModel>()

    override fun initComponent(viewBinding: FragmentRefeshBinding) {
        val movieAdapter = MoviePopularAdapter(::openDetailMovie)
        recycler_view.adapter = movieAdapter

        viewModel.apply {
            listItems.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            })
            firstLoad()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    private fun openDetailMovie(movie: Movie) {
        replaceFragment(
            fragment = DetailMovieFragment.newInstance(movie),
            container = R.id.frame_container,
            TAG = DetailMovieFragment.TAG,
            addToBackStack = true
        )
    }
}