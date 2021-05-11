package com.wmm.wanandroid.features.project.api

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectService
@Inject constructor(retrofit: Retrofit) : ProjectApi {
    private val projectApi by lazy { retrofit.create(ProjectApi::class.java) }

    override fun getHomeArticle(pageNum: Int) = projectApi.getHomeArticle(pageNum)
}
