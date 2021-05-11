package com.wmm.wanandroid.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wmm.wanandroid.R

fun SwipeRefreshLayout.begin() {
    this.isRefreshing = true
}

fun SwipeRefreshLayout.complete() {
    this.isRefreshing = false
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .placeholder(R.mipmap.ic_default_project)
        .error(R.mipmap.ic_default_project)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
