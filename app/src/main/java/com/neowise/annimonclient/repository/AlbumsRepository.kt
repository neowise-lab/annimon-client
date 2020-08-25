package com.neowise.annimonclient.repository

import android.util.Log
import com.neowise.annimonclient.model.Album
import com.neowise.annimonclient.model.Photo
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.json.JSONTokener
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*

object AlbumsRepository {

    private val client = OkHttpClient()

    fun getAlbums(page: Int): List<Album>? {
        try {
            val doc = Jsoup.connect(Urls.GET_PHOTO_ALBUMS + page).get()
            val photoAlbumList: MutableList<Album> = ArrayList()
            val el = doc.select("div[class=list1]")
            val eli = doc.select("div[class=list2]")
            val all = Elements()
            for (i in el.indices) {
                all.add(all.size, el[i])
                all.add(all.size, eli[i])
            }
            for (element in all) photoAlbumList.add(getPhotoAlbum(element))

            return photoAlbumList
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getPhotoAlbum(element: Element): Album {
        val albumId = element.getElementsByTag("a")[0].attr("href")
            .replace("./?act=album&id=", "")
        val name = element.getElementsByTag("a")[1].text()
        val user = element.getElementsByTag("a")[2].text()
        val count = element.select("span[class=gray]").text().split(" ".toRegex()).toTypedArray()[1]
        val photo = element.getElementsByTag("img")[0].attr("src")

        return Album(
            id = albumId,
            name = name,
            author = user,
            count = count,
            imageUrl = "https://annimon.com/albums/$photo"
        )
    }

    fun getPhotos(id: String) : List<Photo>? {
        try {
            val request = Request.Builder()
                .url("${Urls.GET_PHOTOS}$id")
                .build()
            val response = client.newCall(request).execute()
            val json = response.body!!.string()
            val parse = parsePhotos(json)
            return parse
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun parsePhotos(json: String): List<Photo>? {
        return try {
            val postList = mutableListOf<Photo>()
            val rootJson = JSONObject(JSONTokener(json))
            val postsJson = rootJson.getJSONArray("photos")

            for (i in 0 until postsJson.length()) {
                val c = postsJson.getJSONObject(i)
                val id = c.getString("id")
                val name = c.getString("name")
                val photo = c.getString("thumb").replace("http:", "https:")
                val text = c.getString("text")
                postList.add(Photo(id, name, photo, text))
            }
            postList
        }
        catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}