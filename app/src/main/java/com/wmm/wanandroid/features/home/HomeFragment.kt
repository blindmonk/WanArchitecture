package com.wmm.wanandroid.features.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.extension.*
import com.wmm.wanandroid.core.platform.BaseFragment
import com.wmm.wanandroid.features.home.adapter.HomePageAdapter
import com.wmm.wanandroid.features.home.model.HotkeyBean
import com.wmm.wanandroid.features.home.viewmodel.HomeViewModel
import failureVerdict
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * wanAndroid 首页
 *
 * --首页banner
 * --首页文章列表
 * @author wangmengmeng
 * @date 2021-04-26
 */
class HomeFragment : BaseFragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var homePageAdapter: HomePageAdapter
    override fun layoutId() = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        homeViewModel = viewModel(viewModelFactory) {
            observe(hotkey, ::handleHotKeyList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadHotkey()
    }

    private fun loadHotkey() {
        homeViewModel.getHotkey()
    }

    private fun initializeView() {
        homePageAdapter = HomePageAdapter(this)
        viewPager.adapter = homePageAdapter
    }


    private fun handleHotKeyList(hotList: List<HotkeyBean>?) {
        homePageAdapter.hotKeyList = hotList.orEmpty()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = hotList?.get(position)?.name
        }.attach()
    }

    private fun handleFailure(failure: Failure?) {
        notifyWithAction(failureVerdict(failure), R.string.action_refresh, ::loadHotkey)
    }
}