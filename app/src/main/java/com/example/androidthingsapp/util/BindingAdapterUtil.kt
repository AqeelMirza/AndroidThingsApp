package com.example.androidthingsapp.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("drawable")
fun setDrawable(view: ImageView, drawable: Drawable?) = view.setImageDrawable(drawable)