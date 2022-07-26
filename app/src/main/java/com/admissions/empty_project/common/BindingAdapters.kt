package com.admissions.empty_project.common

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun ImageView.setImageUri(path: String){
    Glide.with(context).load(path).into(this)
}