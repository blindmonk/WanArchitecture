package com.wmm.wanandroid.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wmm.wanandroid.features.home.ArticleFragment
import com.wmm.wanandroid.features.home.model.HotkeyBean
import kotlin.properties.Delegates

/**
 * wanAndroid 首页
 * --viewpage 适配器
 *
 * @author wangmengmeng
 * @date 2021-04-26
 */
class HomePageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    internal var hotKeyList: List<HotkeyBean> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = hotKeyList.size

    override fun createFragment(position: Int): Fragment =
        ArticleFragment.create(hotKeyList[position].name)
}