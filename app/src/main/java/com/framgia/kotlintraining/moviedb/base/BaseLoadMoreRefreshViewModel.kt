package com.framgia.kotlintraining.moviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.framgia.kotlintraining.moviedb.utils.constant.Constant
import com.framgia.kotlintraining.moviedb.utils.widgets.EndlessRecyclerOnScrollListener

abstract class BaseLoadMoreRefreshViewModel<Item>() : BaseViewModel() {

    val listItems = MutableLiveData<ArrayList<Item>>()

    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        if (isLoading.value == true || isRefreshing.value == true) return@OnRefreshListener
        isRefreshing.value = true
        refeshData()
    }

    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    var currentPage = MutableLiveData<Int>().apply { value = Constant.FIRST_PAGE - 1 }
    var isLastPage = MutableLiveData<Boolean>().apply { value = false }

    val onScrollListener = object : EndlessRecyclerOnScrollListener(3) {
        override fun onLoadMore() {
            if (isLoading.value == true
                    || isRefreshing.value == true
                    || isLoadMore.value == true
                    || isLastPage.value == true) return
            isLoadMore.value = true
            loadMore()
        }
    }

    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
        isLastPage.value = false
    }

    fun onLoadSuccess(page: Int, items: ArrayList<Item>?) {
        val newList = listItems.value ?: ArrayList()
        newList.addAll(items ?: listOf())
        listItems.value = newList

        currentPage.value = page
        if (currentPage.value == 1) listItems.value?.clear()
        if (isRefreshing.value == true) resetLoadMore()

        isLastPage.value = items?.size ?: 0 < 10
        isLoadMore.value = false
        isLoading.value = false
        isRefreshing.value = false
    }

    fun firsLoad() {
        isLoading.value = true
        loadData(1)
    }

    fun refeshData() {
        loadData(Constant.FIRST_PAGE)
    }

    fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: Constant.FIRST_PAGE)
    }

    abstract fun loadData(page: Int)

    override fun onLoadFail(throwable: Throwable) {
        super.onLoadFail(throwable)
        isRefreshing.value = false
        isLoadMore.value = false
    }
}