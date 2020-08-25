package com.neowise.annimonclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.model.Question
import com.neowise.annimonclient.view.adapter.holder.*
import java.lang.RuntimeException

class QuestionAdapter(
    private var items: List<Question>,
    private val selectListener: (Question) -> Unit,
    private val pageListener: (NavigationType) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            ITEM -> QuestionVH(inflater, parent)
            NAVIGATION -> NavigationVH(inflater, parent)
            else -> throw RuntimeException("Illegal viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size -1) NAVIGATION else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            ITEM -> {
                holder as QuestionVH
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    selectListener(items[position])
                }
            }
            NAVIGATION -> {
                holder as NavigationVH
                holder.bind(pageListener)
            }
        }
    }

    fun setItems(items: List<Question>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}