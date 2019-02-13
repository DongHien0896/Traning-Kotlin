package com.framgia.kotlintraining.moviedb.screen.favorite

import androidx.lifecycle.ViewModelProviders
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_favorite

    override fun initComponent(viewBinding: FragmentFavoriteBinding) {
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }
}