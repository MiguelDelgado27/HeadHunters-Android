package com.headhunters.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.widget.ANImageView
import com.headhunters.R
import com.headhunters.activities.AlbumActivity
import com.headhunters.models.AlbumClass
import kotlinx.android.synthetic.main.content_album.view.*

class AlbumsAdapter(private var albums: List<AlbumClass>) :
        RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {
    class ViewHolder(albumView: View) : RecyclerView.ViewHolder(albumView) {
        var titleTextView: TextView
        var artistTextView: TextView
        var image: ANImageView
        var contentAlbum: ConstraintLayout
        init {
            titleTextView = albumView.title
            artistTextView = albumView.artist
            image = albumView.image
            contentAlbum = albumView.contentAlbum
        }
        fun bindTo(album: AlbumClass) {
            titleTextView.text = album.title
            artistTextView.text = album.artist
            image.setImageUrl(album.image)
            contentAlbum.setOnClickListener {
                val bundle = Bundle()
                bundle.apply {
                    putString("title", album.title)
                    putString("artist", album.artist)
                    putString("image", album.image)
                }
                var intent = Intent(it.context, AlbumActivity::class.java)
                intent.putExtras(bundle) //Intent initializes an activity. context is the current activity
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_album, parent, false))
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(albums[position])
    }
}