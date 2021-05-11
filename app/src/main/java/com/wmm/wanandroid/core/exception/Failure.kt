package com.wmm.wanandroid.core.exception

/**
 *处理错误/失败/异常的基类。
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object JsonSyntaxError : Failure()
    object SocketTimeoutError : Failure()
    class BusinessError(val code: Int?, val msg: String?) : Failure()

    /** * 异常扩展类*/
    abstract class FeatureFailure : Failure()
}