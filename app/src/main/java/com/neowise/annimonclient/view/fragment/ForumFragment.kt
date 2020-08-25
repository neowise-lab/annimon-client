package com.neowise.annimonclient.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.TopicGroup
import com.neowise.annimonclient.view.adapter.ForumCategoryAdapter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_forum.*
import java.io.IOException
import java.io.InputStream


class ForumFragment : Fragment() {

    companion object {
        fun getInstance() : ForumFragment {
            return ForumFragment()
        }
    }

    private lateinit var topicGroups: List<TopicGroup>
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topicGroups = getCategories()
        category_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ForumCategoryAdapter(topicGroups){
                Log.d("TOPIC", it.name)
            }
        }

        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        token = sharedPreferences?.getString("token", "")!!
        unread.visibility = if (token == "") View.GONE else View.VISIBLE
    }

    private fun getCategories(): List<TopicGroup> {
        val stream: InputStream = activity!!.assets.open("categories.json")
        val str: String = convertStreamToString(stream)
        val gson = Gson()
        val categories = gson.fromJson(str, Array<TopicGroup>::class.java)
        return listOf(*categories)
    }

    private fun convertStreamToString(stream: InputStream) : String {
        try {
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            return String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}