package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.PagingItemPageFragmentBinding
import kotlinx.android.synthetic.main.paging_item_key_fragment.*
import kotlinx.android.synthetic.main.paging_item_page_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PagingByPageKeyFragment : BaseFragment<PagingItemPageFragmentBinding, MovieViewModel>() {

    override val viewModel by viewModel<MovieViewModel>()

    override val layoutRes: Int = R.layout.paging_item_page_fragment

    override fun initComponent(viewBinding: PagingItemPageFragmentBinding) {

    }

    companion object {
        fun newInstance() = PagingByPageKeyFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MovieAdapter()
        listItemPage.apply {
            layoutManager =
                LinearLayoutManager(this.context, RecyclerView.VERTICAL, false).apply {
                    isSmoothScrollbarEnabled = true
                    isAutoMeasureEnabled = true
                }
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            var divider = DividerItemDecoration(
                this.context,
                (layoutManager as LinearLayoutManager).orientation
            )
            divider.setDrawable(this.context.getDrawable(R.drawable.divider))
            this.addItemDecoration(divider)
            setAdapter(adapter)
        }

        viewModel.getMovie().observe(this, Observer {
            adapter.submitList(it)
        })

        refreshData.setOnRefreshListener {
            viewModel.refreshData()
            refreshData.isRefreshing = false
        }
    }

}