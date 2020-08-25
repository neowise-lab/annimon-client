package com.neowise.annimonclient.view.adapter.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Topic
import com.neowise.annimonclient.model.TopicGroup

class CategoryVH(
    private val inflater: LayoutInflater, parent: ViewGroup,
    private val listener: (Topic) -> Unit
)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_topic_cat, parent, false)) {

    fun bind(topicGroup: TopicGroup) {
        val name =  itemView.findViewById<TextView>(R.id.category_title)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.linear)

        name.text = topicGroup.name
        linearLayout.removeAllViews()

        for (topic in topicGroup.topics) {
            linearLayout.addView(buildTopic(topic))
        }
    }

    private fun buildTopic(topic: Topic): View {
        val view = inflater.inflate(R.layout.item_topic, null)
        val name = view.findViewById<TextView>(R.id.forum_item_name)

        name.text = topic.name

        view.setOnClickListener {
            listener(topic)
        }

        return view
    }
}