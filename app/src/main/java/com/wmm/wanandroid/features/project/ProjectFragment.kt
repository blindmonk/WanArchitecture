package com.wmm.wanandroid.features.project

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.extension.*
import com.wmm.wanandroid.core.platform.BaseFragment
import com.wmm.wanandroid.features.home.BrowseActivity
import com.wmm.wanandroid.features.project.adapter.ProjectAdapter
import com.wmm.wanandroid.features.project.model.ProjectBean
import com.wmm.wanandroid.features.project.viewmodel.ProjectViewModel
import failureVerdict
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject

/**
 * wanAndroid 首页-项目
 *
 * @author wangmengmeng
 * @date 2021-04-26
 */
class ProjectFragment : BaseFragment() {

    companion object {
        private var canLoadMore = true
        private var pageNum = 0
        private var pageSize = 15
    }

    lateinit var projectViewModel: ProjectViewModel

    @Inject
    lateinit var projectAdapter: ProjectAdapter

    override fun layoutId() = R.layout.fragment_article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        projectViewModel = viewModel(viewModelFactory) {
            observe(project, ::handleArticleList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadArticleList()
    }

    private fun initializeView() {
        recyclerView.adapter = projectAdapter
        val mLinearLayoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = mLinearLayoutManager
        projectAdapter.clickListener = { BrowseActivity.callingIntent(recyclerView.context, it.link) }
        refreshLayout.setOnRefreshListener { loadArticleList() }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (canLoadMore) onRecyclerViewScrolled(mLinearLayoutManager, dy) {
                    canLoadMore = false
                    pageNum++
                    projectViewModel.getProject(pageNum)
                }
            }
        })
    }

    private fun loadArticleList() {
        pageNum = 0
        refreshLayout.begin()
        emptyView.invisible()
        recyclerView.visible()
        projectViewModel.getProject(pageNum)
    }


    private fun handleArticleList(article: ProjectBean?) {
        refreshLayout.complete()
        article?.datas?.let {
            if (article.curPage == 1) {
                projectAdapter.articleList = it
            } else {
                projectAdapter.addAll(it)
            }
            canLoadMore = article.size >= pageSize
        }
    }

    private fun handleFailure(failure: Failure?) {
        recyclerView.invisible()
        emptyView.visible()
        refreshLayout.complete()
        notifyWithAction(failureVerdict(failure), R.string.action_refresh, ::loadArticleList)
    }
}