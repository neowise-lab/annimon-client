package com.neowise.annimonclient.view.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.R

class NavigationVH(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_navigation_buttons, parent, false)) {

    fun bind(listener: (NavigationType) -> Unit) {
        val nextBtn = itemView.findViewById<LinearLayout>(R.id.next_btn)
        val prevBtn = itemView.findViewById<LinearLayout>(R.id.prev_btn)

        nextBtn.setOnClickListener {
            listener(NavigationType.NEXT)
        }
        prevBtn.setOnClickListener {
            listener(NavigationType.PREV)
        }
    }

}