package com.framgia.kotlintraining.moviedb.pagingsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.kotlintraining.moviedb.databinding.CheeseItemBinding

class CheeseAdapter: PagedListAdapter<Cheese, CheeseAdapter.CheeseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder =
        CheeseViewHolder(
            CheeseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class CheeseViewHolder(
        private val cheeseBinding: CheeseItemBinding
    ) : RecyclerView.ViewHolder(cheeseBinding.root) {
        var cheese: Cheese? = null

        fun bindTo(cheese: Cheese?) {
            this.cheese = cheese
            cheeseBinding.cheese = cheese
            cheeseBinding.executePendingBindings()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem == newItem

        }
    }
}
