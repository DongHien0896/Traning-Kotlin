package com.framgia.kotlintraining.moviedb.screen.favorite

import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_favorite

    override val viewModel by viewModel<FavoriteViewModel>()

    override fun initComponent(viewBinding: FragmentFavoriteBinding) {
    }
}