package com.neowise.annimonclient.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.neowise.annimonclient.R
import com.neowise.annimonclient.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var articlesFragment: ArticlesFragment
    private lateinit var albumsFragment: AlbumsFragment
    private lateinit var questionsFragment: QuestionsFragment
    private lateinit var forumFragment: ForumFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment.getInstance()
        articlesFragment = ArticlesFragment.getInstance()
        albumsFragment = AlbumsFragment.getInstance()
        questionsFragment = QuestionsFragment.getInstance()
        forumFragment = ForumFragment.getInstance()

        chats_btn.setOnClickListener {
            // TODO: Реализвать окно чата
        }

        isShowProgressBar = false
        bottom_menu.setOnItemSelectedListener(::changeFragment)
        bottom_menu.setItemSelected(R.id.menu_home, true)
    }

    var isShowProgressBar: Boolean = false
    set(value) {
        Handler().post {
            progress_bar.visibility = if (value) View.VISIBLE else View.GONE
        }
        field = value
    }

    private fun changeFragment(id: Int) {
        val fragment = when(id) {
            R.id.menu_home -> homeFragment
            R.id.menu_albums -> albumsFragment
            R.id.menu_articles -> articlesFragment
            R.id.menu_questions -> questionsFragment
            R.id.menu_forum -> forumFragment
            else -> throw RuntimeException("Illegal fragment id")
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.commit()
    }
}