package com.wmm.wanandroid.core.extension

import androidx.recyclerview.widget.LinearLayoutManager

fun String.Companion.empty() = ""

fun onRecyclerViewScrolled(
    layoutManager: LinearLayoutManager,
    dy: Int,
    loadData: () -> Unit
) {
    val lastVisibleItem: Int = layoutManager.findLastVisibleItemPosition()
    val totalItemCount: Int = layoutManager.itemCount
    if (lastVisibleItem >= totalItemCount - 3 && dy > 0) {
        loadData()
    }
}