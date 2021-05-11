package com.wmm.wanandroid.features.home.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.extension.inflate
import com.wmm.wanandroid.core.extension.loadFromUrl
import com.wmm.wanandroid.features.home.model.ArticleBean
import kotlinx.android.synthetic.main.item_article_list.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ArticleAdapter
@Inject constructor() : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    internal var articleList: MutableList<ArticleBean.Datas> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (ArticleBean.Datas) -> Unit = { _ -> }

    fun addAll(date: MutableList<ArticleBean.Datas>) {
        articleList.addAll(date)
        notifyItemRangeInserted(articleList.size - date.size, articleList.size)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(date: ArticleBean.Datas, clickListener: (ArticleBean.Datas) -> Unit) {
            itemView.articleTitle.text =
                HtmlCompat.fromHtml(date.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
            itemView.articleTime.text = date.niceDate
            itemView.articleType.text = date.superChapterName
            itemView.articleDesc.text = HtmlCompat.fromHtml(date.desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
            itemView.userName.text = date.shareUser.ifEmpty { date.author }
            itemView.articleImage.loadFromUrl(date.envelopePic)
            itemView.setOnClickListener { clickListener(date) }
            itemView.articleImage.visibility = when {
                TextUtils.isEmpty(date.envelopePic) -> View.GONE
                else -> View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(viewGroup.inflate(R.layout.item_article_list))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(articleList[position], clickListener)

    override fun getItemCount() = articleList.size
}
