package com.wmm.wanandroid.core.platform

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.wmm.wanandroid.AndroidApplication
import com.wmm.wanandroid.R.color
import com.wmm.wanandroid.core.di.ApplicationComponent
import javax.inject.Inject


/**
 * Base Fragment
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId(), container, false)

    internal fun notifyWithAction(
        @StringRes message: Int,
        @StringRes actionText: Int,
        action: () -> Any
    ) {
        view?.let {
            val snackBar = Snackbar.make(it, message, Snackbar.LENGTH_INDEFINITE)
            snackBar.setAction(actionText) { _ -> action.invoke() }
            snackBar.setActionTextColor(ContextCompat.getColor(it.context, color.gray))
            snackBar.show()
        }
    }
}
