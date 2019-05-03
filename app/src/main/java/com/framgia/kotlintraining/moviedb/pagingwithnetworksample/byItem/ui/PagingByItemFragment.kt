package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.PagingItemKeyFragmentBinding
import kotlinx.android.synthetic.main.paging_item_key_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PagingByItemFragment :
    BaseFragment<PagingItemKeyFragmentBinding, RedditVewModel>() {

    companion object {
        fun newInstance() = PagingByItemFragment()
    }

    override val viewModel by viewModel<RedditVewModel>()
    override val layoutRes: Int = R.layout.paging_item_key_fragment

    override fun initComponent(viewBinding: PagingItemKeyFragmentBinding) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = RedditAdapter()
        listItemKey.apply {
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

        viewModel.getReddit().observe(this, Observer {
            adapter.submitList(it)
        })

        refresh.setOnRefreshListener {
            viewModel.refreshData()
            refresh.isRefreshing = false
        }
    }
}