package com.gabriel.kotlinstars.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.squareup.picasso.Picasso

fun ImageView.loadFromUrl(url: String) =
    Picasso.get().load(url).into(this)