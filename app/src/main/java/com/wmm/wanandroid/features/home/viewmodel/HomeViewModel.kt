package com.wmm.wanandroid.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wmm.wanandroid.core.UseCase
import com.wmm.wanandroid.core.platform.BaseViewModel
import com.wmm.wanandroid.features.home.api.GetHotkey
import com.wmm.wanandroid.features.home.model.HotkeyBean
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val GetHotkey: GetHotkey
) : BaseViewModel() {

    private val _hot: MutableLiveData<List<HotkeyBean>> = MutableLiveData()
    val hotkey: LiveData<List<HotkeyBean>> = _hot

    fun getHotkey() =
        GetHotkey(UseCase.None()) { it.fold(::handleFailure, ::handleBanner) }

    private fun handleBanner(banner: List<HotkeyBean>) {
        _hot.value = banner
    }
}