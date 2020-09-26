package com.milen.utils

import android.view.View
import android.widget.ImageView

fun View.beVisible(){
    visibility = View.VISIBLE
}

fun View.beInvisible(){
    visibility = View.INVISIBLE
}

fun View.beGone(){
    visibility = View.GONE
}

fun ImageView.loadUrl(imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        ImageLoaderLib.loadImageUrlIntoView(imageUrl, this)
    }
}