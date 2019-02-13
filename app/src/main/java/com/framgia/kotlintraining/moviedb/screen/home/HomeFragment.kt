package com.framgia.kotlintraining.moviedb.screen.home

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_home


    override fun initComponent(viewBinding: FragmentHomeBinding) {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onBackPress() {
        super.onBackPress()
    }

}