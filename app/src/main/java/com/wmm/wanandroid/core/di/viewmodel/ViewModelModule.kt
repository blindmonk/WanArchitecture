package com.wmm.wanandroid.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wmm.wanandroid.features.home.viewmodel.HomeViewModel
import com.wmm.wanandroid.features.home.viewmodel.ArticleViewModel
import com.wmm.wanandroid.features.project.viewmodel.ProjectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHotkeyModel(treeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindsQueryArticleModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectViewModel::class)
    abstract fun bindsProjectViewModel(projectViewModel: ProjectViewModel): ViewModel

}