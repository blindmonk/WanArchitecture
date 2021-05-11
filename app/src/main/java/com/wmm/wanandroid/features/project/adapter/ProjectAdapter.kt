package com.wmm.wanandroid.features.project.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.extension.inflate
import com.wmm.wanandroid.core.extension.loadFromUrl
import com.wmm.wanandroid.features.project.model.ProjectBean
import kotlinx.android.synthetic.main.item_project_list.view.*
import javax.inject.Inject
import kotlin.properties.Delegates


class ProjectAdapter
@Inject constructor() : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    internal var articleList: MutableList<ProjectBean.Datas> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (ProjectBean.Datas) -> Unit = { _ -> }

    fun addAll(date: MutableList<ProjectBean.Datas>) {
        articleList.addAll(date)
        notifyItemRangeInserted(articleList.size - date.size, articleList.size)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(date: ProjectBean.Datas, clickListener: (ProjectBean.Datas) -> Unit) {
            itemView.projectTitle.text = HtmlCompat.fromHtml(date.title,HtmlCompat.FROM_HTML_MODE_LEGACY)
            itemView.projectDesc.text = date.desc
            itemView.projectTime.text = date.niceDate
            itemView.projectType.text = date.chapterName
            itemView.projectImage.loadFromUrl(date.envelopePic)
//            itemView.userName.text = date.author
            itemView.setOnClickListener { clickListener(date) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(viewGroup.inflate(R.layout.item_project_list))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(articleList[position], clickListener)

    override fun getItemCount() = articleList.size
}
