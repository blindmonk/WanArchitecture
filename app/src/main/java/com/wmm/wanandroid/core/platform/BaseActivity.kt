package com.wmm.wanandroid.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wmm.wanandroid.AndroidApplication
import com.wmm.wanandroid.core.di.ApplicationComponent
import javax.inject.Inject

/**
 * Base Activity
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initializeView()
    }

    abstract fun initializeView()
}
