package com.framgia.kotlintraining.moviedb.screen.home

import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override val viewModel by viewModel<HomeViewModel>()

    override fun initComponent(viewBinding: FragmentHomeBinding) {

    }
}