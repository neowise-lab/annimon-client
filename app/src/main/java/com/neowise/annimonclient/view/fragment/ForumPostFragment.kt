package com.neowise.annimonclient.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neowise.annimonclient.R

class ForumPostFragment : Fragment() {

    companion object {
        fun getInstance() : ForumPostFragment {
            return ForumPostFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forum_posts, container, false)
    }
}