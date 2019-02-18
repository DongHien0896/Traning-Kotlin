package com.framgia.kotlintraining.moviedb.screen.detail

import android.os.Bundle
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.databinding.FragmentMovieDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : BaseFragment<FragmentMovieDetailBinding,
        DetailMovieViewModel>
    () {

    companion object {

        private const val KEY_MOVIE = "MOVIE"
        const val TAG = "MovieDetailFragment"

        fun newInstance(movie: Movie) = DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_MOVIE, movie)
            }
        }
    }

    override val viewModel by viewModel<DetailMovieViewModel>()

    override val layoutRes: Int
        get() = R.layout.fragment_movie_detail

    override fun initComponent(viewBinding: FragmentMovieDetailBinding) {
        viewModel.movie.value = arguments?.getParcelable<Movie>(KEY_MOVIE)?.let { it }
    }

}