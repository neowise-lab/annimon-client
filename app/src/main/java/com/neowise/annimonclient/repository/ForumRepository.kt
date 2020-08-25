package com.neowise.annimonclient.repository

import android.util.Log
import com.neowise.annimonclient.model.Topic
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.json.JSONTokener

object ForumRepository {

    private val client = OkHttpClient()

    fun getTopics(section: String, offset: Int = 0): List<Topic>? {
        try {
            val request = Request.Builder()
                .url("${Urls.FORUM_GET_TOPICS}$section&start=0&limit=$offset")
                .build()
            val response = client.newCall(request).execute()
            val json = response.body!!.string()
            Log.d("RESPONSE", json)
            val parse = parse(json)
            return parse
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun parse(json: String): List<Topic>? {
        val topics = mutableListOf<Topic>()
        val rootJson = JSONObject(JSONTokener(json))
        val jsonArray = rootJson.getJSONArray("topics")

        for (i in 0..jsonArray.length()-1) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("topic")
            val name = jsonObject.getString("title")
            topics.add(Topic(id, name))
        }
        Log.d("RESPONSE", topics.toString())
        return topics
    }
}