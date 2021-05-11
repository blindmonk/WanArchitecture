package com.wmm.wanandroid.features.home.model

import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.functional.Either
import com.wmm.wanandroid.core.platform.NetworkHandler
import com.wmm.wanandroid.core.functional.Either.Left
import com.wmm.wanandroid.features.home.api.HomeService
import request
import javax.inject.Inject

interface HomeRepository {
    suspend fun hotKey(): Either<Failure, List<HotkeyBean>>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: HomeService
    ) : HomeRepository {

        override suspend fun hotKey(): Either<Failure, List<HotkeyBean>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.hotkey(),
                    { it },
                    HotkeyBean.empty
                )
                false -> Left(Failure.NetworkConnection)
            }
        }
    }
}
