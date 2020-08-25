package com.neowise.annimonclient.repository

import com.neowise.annimonclient.model.Question
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.util.*

object QuestionsRepository {

    fun getQuestions(page: Int): MutableList<Question>? {
        try {
            val doc = Jsoup.connect(Urls.QA_GET_TOPICS + page).get()
            val questionsList: MutableList<Question> = ArrayList()
            val all = doc.select("div[class=statmenu qa_preview]")

            for (element in all)
                questionsList += getQuestion(element)

            return questionsList
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getQuestion(element: Element): Question {
        val id = element.getElementsByTag("a")[1].attr("href").replace("./","")
        val name = element.getElementsByTag("h2")[0].text()
        val user = element.getElementsByTag("a")[0].text()
        var count = element.getElementsByTag("small")[0].text()
        count = count.split(" ".toRegex()).toTypedArray()[0]
        var rating = element.getElementsByTag("small")[1].text()
        rating = rating.split(" ".toRegex()).toTypedArray()[0]
        var views = element.getElementsByTag("small")[2].text()
        views = views.split(" ".toRegex()).toTypedArray()[0]
        var time = element.select("div[class=qa_info]")[0].text()
        time = time.replace(" ", "")
        time = time.replace(user, "")

        return Question(
            id = id,
            author = user,
            name = name,
            count = count,
            rating = rating,
            views = views,
            time = time
        )
    }
}