package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.databinding.ItemMoviePageBinding

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(callBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemMoviePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class MovieViewHolder(
        private val itemBinding: ItemMoviePageBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        var movie: Movie? = null
        fun bindTo(movie: Movie?) {
            this.movie = movie
            itemBinding.movie = movie
            itemBinding.executePendingBindings()
        }
    }

    companion object {
        private val callBack = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.mIdMovie == newItem.mIdMovie
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}