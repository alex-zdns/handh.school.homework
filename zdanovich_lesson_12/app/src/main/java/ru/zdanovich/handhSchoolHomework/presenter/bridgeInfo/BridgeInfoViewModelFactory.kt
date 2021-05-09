package ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BridgeInfoViewModelFactory  : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        BridgeInfoViewModel::class.java -> BridgeInfoViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}