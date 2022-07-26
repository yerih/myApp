package com.admissions.empty_project.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun ImageView.setImageUri(path: String? = ""){
    path?.let {
        if(path.isEmpty())return
        Glide.with(context).load(path).into(this)
    }
}