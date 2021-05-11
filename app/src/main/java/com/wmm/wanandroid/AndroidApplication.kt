package com.wmm.wanandroid

import android.app.Application
import com.wmm.wanandroid.core.di.ApplicationComponent
import com.wmm.wanandroid.core.di.ApplicationModule
import com.wmm.wanandroid.core.di.DaggerApplicationComponent


class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}
