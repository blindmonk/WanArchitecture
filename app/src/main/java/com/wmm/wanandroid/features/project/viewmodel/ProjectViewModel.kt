package com.wmm.wanandroid.features.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wmm.wanandroid.core.platform.BaseViewModel
import com.wmm.wanandroid.features.project.api.GetProject
import com.wmm.wanandroid.features.project.model.ProjectBean
import javax.inject.Inject

class ProjectViewModel
@Inject constructor(
    private val getProject: GetProject
) : BaseViewModel() {

    private val _project: MutableLiveData<ProjectBean> = MutableLiveData()
    val project: LiveData<ProjectBean> = _project

    fun getProject(pageNum: Int) =
        getProject(GetProject.Params(pageNum)) { it.fold(::handleFailure, ::handleArticle) }

    private fun handleArticle(project: ProjectBean) {
        _project.value = project
    }
}