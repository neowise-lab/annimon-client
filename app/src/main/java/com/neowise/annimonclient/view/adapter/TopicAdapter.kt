package com.neowise.annimonclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.model.Topic
import com.neowise.annimonclient.view.adapter.holder.TopicVH

class TopicAdapter(private var items: List<Topic>) : RecyclerView.Adapter<TopicVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicVH {
        val inflater = LayoutInflater.from(parent.context)
        return TopicVH(inflater, parent)
    }

    override fun onBindViewHolder(holder: TopicVH, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<Topic>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}