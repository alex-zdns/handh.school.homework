package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.create
import ru.zdanovich.handhSchoolHomework.data.network.NetworkModule

class BridgesListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        BridgesListViewModel::class.java -> BridgesListViewModel(
            NetworkModule.retrofit.create()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}