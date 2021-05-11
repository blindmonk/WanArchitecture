package com.wmm.wanandroid.features.home.model

import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.functional.Either
import com.wmm.wanandroid.core.platform.NetworkHandler
import com.wmm.wanandroid.core.functional.Either.Left
import com.wmm.wanandroid.features.home.api.HomeService
import request
import javax.inject.Inject

interface ArticleRepository {
    suspend fun queryArticle(page: Int, k: String): Either<Failure, ArticleBean>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: HomeService
    ) : ArticleRepository {

        override suspend fun queryArticle(page: Int, k: String): Either<Failure, ArticleBean> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.queryArticle(page, k),
                    { it },
                    ArticleBean.empty
                )
                false -> Left(Failure.NetworkConnection)
            }
        }
    }
}
