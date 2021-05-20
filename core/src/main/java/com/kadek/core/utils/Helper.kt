package com.kadek.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Helper {

    const val POSTER_ENDPOINT = "https://image.tmdb.org/t/p/"
    const val POSTER_W185 = "w185"
    const val POSTER_W780 = "w780"

    fun setImage(context: Context, imagePath: String, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}