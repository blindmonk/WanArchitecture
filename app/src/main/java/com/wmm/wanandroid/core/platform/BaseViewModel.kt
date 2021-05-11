package com.wmm.wanandroid.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.exception.Failure
import okhttp3.internal.notify
import okhttp3.internal.wait

/**
 * 失败处理的ViewModel类。
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }
}