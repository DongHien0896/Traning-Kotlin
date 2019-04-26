package com.framgia.kotlintraining.moviedb.pagingsample.main

import android.app.Application
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.PagingDbFragmentBinding
import com.framgia.kotlintraining.moviedb.pagingsample.CheeseAdapter
import com.framgia.kotlintraining.moviedb.pagingsample.CheeseViewModel
import kotlinx.android.synthetic.main.paging_db_fragment.*

class PagingDbFragment(private val application: Application) :
    BaseFragment<PagingDbFragmentBinding, PagingViewModel>() {

    companion object {
        fun newInstance(application: Application) = PagingDbFragment(application)
    }

    override val viewModel: PagingViewModel
        get() = PagingViewModel()
    override val layoutRes: Int
        get() = R.layout.paging_db_fragment

    override fun initComponent(viewBinding: PagingDbFragmentBinding) {
        val viewModel = CheeseViewModel(application)
        val adapter = CheeseAdapter()
        recyclerCheese.apply {
            layoutManager =
                LinearLayoutManager(this.context, RecyclerView.VERTICAL, false).apply {
                    isSmoothScrollbarEnabled = true
                    isAutoMeasureEnabled = true
                }
            setAdapter(adapter)
        }
        viewModel.allCheese.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}