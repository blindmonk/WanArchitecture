package com.wmm.wanandroid.features.home.api

import com.wmm.wanandroid.core.UseCase
import com.wmm.wanandroid.features.home.model.ArticleBean
import com.wmm.wanandroid.features.home.model.ArticleRepository
import javax.inject.Inject

class QueryArticle
@Inject constructor(private val articleRepository: ArticleRepository) :
    UseCase<ArticleBean, QueryArticle.Params>() {

    override suspend fun run(params: Params) = articleRepository.queryArticle(params.page, params.k)

    data class Params(val page: Int, val k: String)
}
