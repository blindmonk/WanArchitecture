package com.wmm.wanandroid.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wmm.wanandroid.core.platform.BaseViewModel
import com.wmm.wanandroid.features.home.api.QueryArticle
import com.wmm.wanandroid.features.home.model.ArticleBean
import javax.inject.Inject

class ArticleViewModel
@Inject constructor(
    private val queryArticle: QueryArticle
) : BaseViewModel() {

    private val _query: MutableLiveData<ArticleBean> = MutableLiveData()
    val query: LiveData<ArticleBean> = _query

    fun queryArticle(page: Int, k: String) =
        queryArticle(QueryArticle.Params(page,k)) { it.fold(::handleFailure, ::handleArticle) }

    private fun handleArticle(article: ArticleBean) {
        _query.value = article
    }
}