package com.neowise.annimonclient.view.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Topic

class TopicVH(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_topic, parent, false)) {

    fun bind(topic: Topic) {
        val name =  itemView.findViewById<TextView>(R.id.forum_item_name)
        name.text = topic.name
    }
}