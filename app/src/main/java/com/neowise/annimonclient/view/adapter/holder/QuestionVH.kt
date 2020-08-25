package com.neowise.annimonclient.view.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Question

class QuestionVH(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_question, parent, false)) {

    fun bind(question: Question) {
        val name =  itemView.findViewById<TextView>(R.id.question_title)
        val count =  itemView.findViewById<TextView>(R.id.question_answer_count)
        val author =  itemView.findViewById<TextView>(R.id.question_author)
        val rating =  itemView.findViewById<TextView>(R.id.question_rating)
        val views =  itemView.findViewById<TextView>(R.id.question_views)
        val date =  itemView.findViewById<TextView>(R.id.question_date)

        name.text = question.name
        count.text = question.count
        author.text = question.author
        rating.text = question.rating
        views.text = question.views
        date.text = question.time
    }
}