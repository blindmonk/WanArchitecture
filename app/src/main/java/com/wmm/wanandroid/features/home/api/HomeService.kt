package com.wmm.wanandroid.features.home.api

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeService
@Inject constructor(retrofit: Retrofit) : HomeApi {
    private val homeApi by lazy { retrofit.create(HomeApi::class.java) }

    override fun hotkey() = homeApi.hotkey()

    override fun queryArticle(page: Int, key: String) = homeApi.queryArticle(page, key)
}
