package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import com.framgia.kotlintraining.moviedb.databinding.RedditItemBinding

class RedditAdapter : PagedListAdapter<RedditPost, RedditAdapter.RedditViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder =
        RedditViewHolder(
            RedditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class RedditViewHolder(
        private val redditItemBinding: RedditItemBinding
    ) : RecyclerView.ViewHolder(redditItemBinding.root) {
        var reddit: RedditPost? = null
        fun bindTo(reddit: RedditPost?) {
            this.reddit = reddit
            redditItemBinding.reddit = reddit
            redditItemBinding.executePendingBindings()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem == newItem

        }
    }
}