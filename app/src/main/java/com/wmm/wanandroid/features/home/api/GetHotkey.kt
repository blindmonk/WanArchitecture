package com.wmm.wanandroid.features.home.api

import com.wmm.wanandroid.core.UseCase
import com.wmm.wanandroid.features.home.model.HomeRepository
import com.wmm.wanandroid.features.home.model.HotkeyBean
import javax.inject.Inject

class GetHotkey
@Inject constructor(private val homeRepository: HomeRepository) :
    UseCase<List<HotkeyBean>, UseCase.None>() {

    override suspend fun run(params: None) = homeRepository.hotKey()
}

