package com.wmm.wanandroid.core.platform
import java.io.Serializable


class BaseBean<T> : Serializable {
    var errorCode = -91275
    var errorMsg: String? = null
    internal var data: T? = null
}