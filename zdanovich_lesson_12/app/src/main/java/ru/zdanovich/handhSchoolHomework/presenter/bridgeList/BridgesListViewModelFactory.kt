package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BridgesListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        BridgesListViewModel::class.java -> BridgesListViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}