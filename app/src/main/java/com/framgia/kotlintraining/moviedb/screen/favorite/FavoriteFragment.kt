package com.framgia.kotlintraining.moviedb.screen.favorite

import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentRefeshBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentRefeshBinding, FavoriteViewModel>() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_refesh

    override val viewModel by viewModel<FavoriteViewModel>()

    override fun initComponent(viewBinding: FragmentRefeshBinding) {
    }
}