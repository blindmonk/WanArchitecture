package com.wmm.wanandroid.core.di


import com.wmm.wanandroid.AndroidApplication
import com.wmm.wanandroid.core.di.viewmodel.ViewModelModule
import com.wmm.wanandroid.features.home.HomeFragment
import com.wmm.wanandroid.features.home.ArticleFragment
import com.wmm.wanandroid.features.project.ProjectFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)

    fun inject(projectFragment: ProjectFragment)

    fun inject(romeFragment: HomeFragment)

    fun inject(articleFragment: ArticleFragment)
}
