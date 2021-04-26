package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.create
import ru.zdanovich.handhSchoolHomework.data.network.NetworkModule
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepositoryImpl

class BridgesListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        BridgesListViewModel::class.java -> BridgesListViewModel(
            BridgesRepositoryImpl(
                NetworkModule.retrofit.create()
            )
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}