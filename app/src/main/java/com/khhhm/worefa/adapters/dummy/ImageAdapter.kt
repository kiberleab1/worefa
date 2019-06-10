package com.khhhm.worefa.adapters.dummy

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation

@BindingAdapter("imageFromUrl")
fun catchImage(view:ImageView,imageurl:String) {
    Glide.with(view.context)
        .load("https://static.pexels.com/photos/264101/pexels-photo-264101.jpeg")
        .into(view)
}