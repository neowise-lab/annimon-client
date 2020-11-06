package com.neowise.annimonclient.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Album
import com.neowise.annimonclient.repository.AlbumsRepository
import com.neowise.annimonclient.view.MainActivity
import com.neowise.annimonclient.view.PhotoViewActivity
import com.neowise.annimonclient.view.adapter.AlbumAdapter
import com.neowise.annimonclient.view.adapter.holder.NavigationType
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumsFragment : Fragment() {

    companion object {
        fun getInstance() : AlbumsFragment {
            return AlbumsFragment()
        }
    }

    private lateinit var albumAdapter: AlbumAdapter
    private var page = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumAdapter = AlbumAdapter(listOf(),
            selectListener = ::onSelect,
            pageListener = ::onPageChange
        )

        albums_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter
        }

        albums_refresher.setOnRefreshListener {
            fetchData()
        }

        fetchData()
    }

    private fun fetchData() {
        showProgress(true)

        GlobalScope.launch(Dispatchers.IO) {
            val elems = AlbumsRepository.getAlbums(page)

            GlobalScope.launch(Dispatchers.Main) {
                updateListData(elems)
            }
        }
    }

    private fun updateListData(data: List<Album>?) {
        if (data == null) {
            Toast.makeText(activity, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
        }
        else {
            albumAdapter.setItems(data)
        }
        albums_refresher?.isRefreshing = false
        albums_recycler.scrollToPosition(0)
        showProgress(false)
    }

    private fun onSelect(album: Album) {
        val intent = Intent(context, PhotoViewActivity::class.java)
        intent.putExtra("album", album.id)
        startActivity(intent)
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