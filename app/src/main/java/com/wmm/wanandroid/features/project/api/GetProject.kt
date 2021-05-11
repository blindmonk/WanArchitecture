package com.wmm.wanandroid.features.project.api

import com.wmm.wanandroid.core.UseCase
import com.wmm.wanandroid.features.project.model.ProjectBean
import com.wmm.wanandroid.features.project.model.ProjectRepository
import javax.inject.Inject

class GetProject
@Inject constructor(private val projectRepository: ProjectRepository) :
    UseCase<ProjectBean, GetProject.Params>() {

    override suspend fun run(params: Params) = projectRepository.getProject(params.pageNum)

    data class Params(val pageNum: Int)
}
