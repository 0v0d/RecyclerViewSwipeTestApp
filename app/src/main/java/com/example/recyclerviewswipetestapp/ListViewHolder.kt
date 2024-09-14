package com.example.recyclerviewswipetestapp

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewswipetestapp.databinding.LayoutItemBinding

class ListViewHolder(
    private val binding: LayoutItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(keyword:Fruit, onItemClick: (Fruit) -> Unit) {
        binding.keyword = keyword
        binding.root.setOnClickListener {
            onItemClick(keyword)
        }
        binding.executePendingBindings()
    }
}
