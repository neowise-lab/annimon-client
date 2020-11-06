package com.neowise.annimonclient.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Question
import com.neowise.annimonclient.repository.QuestionsRepository
import com.neowise.annimonclient.view.MainActivity
import com.neowise.annimonclient.view.adapter.QuestionAdapter
import com.neowise.annimonclient.view.adapter.holder.NavigationType
import kotlinx.android.synthetic.main.fragment_qa_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuestionsFragment : Fragment() {

    companion object {
        fun getInstance() : QuestionsFragment {
            return QuestionsFragment()
        }
    }

    private var page = 0
    private var questionAdapter = QuestionAdapter(
        listOf(),
        selectListener = ::onSelect,
        pageListener = ::onPageChange
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qa_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questions_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = questionAdapter
        }

        question_refresher.setOnRefreshListener {
            fetchData()
        }

        fetchData()
    }

    private fun updateListData(data: List<Question>?) {
        if (data == null)
            Toast.makeText(activity, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
        else
            questionAdapter.setItems(data)

        question_refresher?.isRefreshing = false
        questions_recycler.scrollToPosition(0)
        showProgress(false)
    }

    private fun fetchData() {
        showProgress(true)

        GlobalScope.launch(Dispatchers.IO) {

            val elems = QuestionsRepository.getQuestions(page)

            GlobalScope.launch(Dispatchers.Main) {
                updateListData(elems)
            }
        }
    }

    private fun onSelect(question: Question) {
//        TODO("Implement question view activity")
    }

    private fun onPageChange(navigationType: NavigationType) {
        page += when(navigationType) {
            NavigationType.NEXT -> 20
            NavigationType.PREV -> -20
        }
        fetchData()
    }

    private fun showProgress(isShow: Boolean) {
        if (activity != null) {
            (activity as MainActivity).isShowProgressBar = isShow
        }
    }
}