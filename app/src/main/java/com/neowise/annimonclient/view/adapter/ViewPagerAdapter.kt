package com.neowise.annimonclient.view.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.neowise.annimonclient.model.Photo
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val context: Context, private val imageUrls: List<Photo>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d("PHOTOS", "instantinate: " + imageUrls[position].image)
        val imageView = ImageView(context)
        Picasso.get()
            .load(imageUrls[position].image)
            .into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as View)
    }
}