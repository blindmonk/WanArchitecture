package com.wmm.wanandroid.features.home.api

import com.wmm.wanandroid.core.platform.BaseBean
import com.wmm.wanandroid.features.home.model.ArticleBean
import com.wmm.wanandroid.features.home.model.HotkeyBean
import retrofit2.Call
import retrofit2.http.*

internal interface HomeApi {

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun queryArticle(
        @Path("page") page: Int,
        @Field("k") key: String
    ): Call<BaseBean<ArticleBean>>

    @GET("hotkey/json")
    fun hotkey(): Call<BaseBean<List<HotkeyBean>>>
}
