package com.wmm.wanandroid.features.project.api

import com.wmm.wanandroid.core.platform.BaseBean
import com.wmm.wanandroid.features.project.model.ProjectBean
import retrofit2.Call
import retrofit2.http.*

internal interface ProjectApi {

    @GET("project/list/{pageNum}/json")
    fun getHomeArticle(@Path("pageNum") pageNum: Int): Call<BaseBean<ProjectBean>>
}
