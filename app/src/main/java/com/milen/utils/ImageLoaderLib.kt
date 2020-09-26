package com.milen.utils

import android.widget.ImageView
import com.milen.androidcodecamping.R
import com.squareup.picasso.Picasso

class ImageLoaderLib {
    companion object {
        fun loadImageUrlIntoView(imageUrl: String, imageView: ImageView) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_media)
                .into(imageView)
        }
    }
}