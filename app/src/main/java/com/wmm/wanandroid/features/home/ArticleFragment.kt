package com.wmm.wanandroid.features.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.extension.*
import com.wmm.wanandroid.core.platform.BaseFragment
import com.wmm.wanandroid.features.home.adapter.ArticleAdapter
import com.wmm.wanandroid.features.home.model.ArticleBean
import com.wmm.wanandroid.features.home.viewmodel.ArticleViewModel
import failureVerdict
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject

/**
 * wanAndroid 首页-文章
 *
 * @author wangmengmeng
 * @date 2021-04-26
 */
class ArticleFragment : BaseFragment() {

    companion object {
        private var canLoadMore = true
        private var pageNum = 0
        private var pageSize = 19
        const val PARAM_NAME = "param_name"

        fun create(name: String): ArticleFragment {
            val fragment = ArticleFragment()
            fragment.arguments = Bundle().apply {
                putString(PARAM_NAME, name)
            }
            return fragment
        }
    }

    lateinit var articleViewModel: ArticleViewModel
    lateinit var articleName: String

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun layoutId() = R.layout.fragment_article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        articleViewModel = viewModel(viewModelFactory) {
            observe(query, ::handleArticleList)
            failure(failure, ::handleFailure)
        }
        articleName = (arguments?.get(PARAM_NAME).toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadArticleList()
    }

    private fun initializeView() {
        recyclerView.adapter = articleAdapter
        val mLinearLayoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = mLinearLayoutManager
        articleAdapter.clickListener = { BrowseActivity.callingIntent(recyclerView.context, it.link) }
        refreshLayout.setOnRefreshListener { loadArticleList() }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (canLoadMore) onRecyclerViewScrolled(mLinearLayoutManager, dy) {
                    canLoadMore = false
                    pageNum++
                    articleViewModel.queryArticle(pageNum, articleName)
                }
            }
        })
    }

    private fun loadArticleList() {
        pageNum = 0
        refreshLayout.begin()
        emptyView.invisible()
        recyclerView.visible()
        articleViewModel.queryArticle(pageNum, articleName)
    }


    private fun handleArticleList(article: ArticleBean?) {
        refreshLayout.complete()
        article?.datas?.let {
            if (article.curPage == 1) {
                articleAdapter.articleList = it
            } else {
                articleAdapter.addAll(it)
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