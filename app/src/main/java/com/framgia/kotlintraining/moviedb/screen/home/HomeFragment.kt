package com.framgia.kotlintraining.moviedb.screen.home

import androidx.lifecycle.Observer
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.databinding.FragmentHomeBinding
import com.framgia.kotlintraining.moviedb.screen.home.adapter.MoviePopularAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override val viewModel by viewModel<HomeViewModel>()

    override fun initComponent(viewBinding: FragmentHomeBinding) {
        val movieAdapter = MoviePopularAdapter(::openDetailMovie)
        recycler_view.adapter = movieAdapter

        viewModel.apply {
            listItems.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            })
            firsLoad()

        }

    }

    private fun openDetailMovie(movie: Movie) {
        showMessage("open detail")
    }
}