package com.neowise.annimonclient.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Photo
import com.neowise.annimonclient.repository.AlbumsRepository
import com.neowise.annimonclient.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_photo_view.*
import kotlinx.android.synthetic.main.activity_photo_view.progress_bar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoViewActivity : AppCompatActivity() {

    private var photoCount: Int = 0
    private var photoList: List<Photo> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                initPhotoIndicators(position)
            }
        })

        back_button.setOnClickListener {
            finish()
        }

        val id = intent.getStringExtra("album")!!

        GlobalScope.launch(Dispatchers.IO) {
            val elems = AlbumsRepository.getPhotos(id)
            GlobalScope.launch(Dispatchers.Main) {
                updateData(elems)
            }
        }
    }

    private fun initPhotoIndicators(position: Int) {
        if (photoList.isEmpty()) return

        photo_name.text = photoList[position].name
        photo_text.text = photoList[position].text
        indicator.text = "${(position + 1)} из $photoCount"
    }

    private fun updateData(elems: List<Photo>?) {
        if (elems == null) return
        this.photoList = elems
        this.photoCount = elems.size
        val viewPagerAdapter = ViewPagerAdapter(this, elems)
        view_pager.adapter = viewPagerAdapter
        view_pager.currentItem = 0

        progress_bar.visibility = View.GONE
        initPhotoIndicators(0)
    }
}