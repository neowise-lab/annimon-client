package com.neowise.annimonclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.model.TopicGroup
import com.neowise.annimonclient.model.Topic
import com.neowise.annimonclient.view.adapter.holder.CategoryVH

class ForumCategoryAdapter(private val items: List<TopicGroup>, private val listener: (Topic) -> Unit)
    : RecyclerView.Adapter<CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryVH(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}