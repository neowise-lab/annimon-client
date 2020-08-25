package com.neowise.annimonclient.view.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neowise.annimonclient.R
import com.neowise.annimonclient.model.Album
import com.squareup.picasso.Picasso

class AlbumVH(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_album, parent, false)) {

    private lateinit var album: Album

    fun bind(album: Album) {
        this.album = album

        val name =  itemView.findViewById<TextView>(R.id.album_name)
        val count =  itemView.findViewById<TextView>(R.id.photo_count)
        val author =  itemView.findViewById<TextView>(R.id.album_author)
        val image =  itemView.findViewById<ImageView>(R.id.album_image)

        name.text = album.name
        count.text = album.count
        author.text = album.author
        Picasso.get()
            .load(album.imageUrl)
            .placeholder(R.drawable.not_found)
            .into(image)
    }
}