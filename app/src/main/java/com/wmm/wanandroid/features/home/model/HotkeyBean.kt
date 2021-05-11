package com.wmm.wanandroid.features.home.model

data class HotkeyBean(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
) {

    companion object {
        val empty = mutableListOf<HotkeyBean>()
    }
}
