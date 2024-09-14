package com.example.recyclerviewswipetestapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerviewswipetestapp.databinding.LayoutItemBinding

class ListViewAdapter(
    private val onItemClick: (Fruit) -> Unit
) : ListAdapter<Fruit, ListViewHolder>(
    diffUtilItemCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = LayoutItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder, position: Int
    ) {
        holder.bind(getItem(position), onItemClick)
    }

    private companion object {
        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Fruit>() {
            override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean =
                oldItem.name == newItem.name

            override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean =
                oldItem == newItem
        }
    }
}