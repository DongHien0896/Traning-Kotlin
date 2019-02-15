package com.framgia.kotlintraining.moviedb.base

import androidx.lifecycle.MutableLiveData

abstract class BaseLoadMoreRefreshViewModel<Item>() : BaseViewModel() {
    val listItems = MutableLiveData<ArrayList<Item>>()
    fun onLoadSuccess(page: Int, items: ArrayList<Item>?) {
        val newList = listItems.value ?: ArrayList()
        newList.addAll(items ?: listOf())
        listItems.value = newList
    }

    fun firsLoad() {
        isLoading.value = true
        loadData(1)
    }

    abstract fun loadData(page: Int)

    override fun onLoadFail(throwable: Throwable) {
        super.onLoadFail(throwable)
    }
}