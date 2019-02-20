package com.framgia.kotlintraining.moviedb.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseRecyclerAdapter
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.databinding.ItemMovieBinding

class MoviePopularAdapter(
    private val itemClickListener: ((Movie) -> Unit)? = null
) : BaseRecyclerAdapter<Movie>(
    callBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.mIdMovie == newItem.mIdMovie
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding =
        DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context), R.layout.item_movie,
            parent, false
        ).apply {
            root.setOnClickListener {
                item?.apply {
                    itemClickListener?.invoke(this)
                }
            }
        }

    override fun bind(binding: ViewDataBinding, item: Movie) {
        if (binding is ItemMovieBinding) binding.item = item
    }
}
