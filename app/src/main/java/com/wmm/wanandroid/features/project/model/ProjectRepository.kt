package com.wmm.wanandroid.features.project.model

import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.functional.Either
import com.wmm.wanandroid.core.platform.NetworkHandler
import com.wmm.wanandroid.core.functional.Either.Left
import com.wmm.wanandroid.features.project.api.ProjectService
import request
import javax.inject.Inject

interface ProjectRepository {
    suspend fun getProject(pageNum: Int): Either<Failure, ProjectBean>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: ProjectService
    ) : ProjectRepository {

        override suspend fun getProject(pageNum: Int): Either<Failure, ProjectBean> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.getHomeArticle(pageNum),
                    { it },
                    ProjectBean.empty
                )
                false -> Left(Failure.NetworkConnection)
            }
        }
    }
}
