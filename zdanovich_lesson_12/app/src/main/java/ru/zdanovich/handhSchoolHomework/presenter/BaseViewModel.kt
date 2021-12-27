package ru.zdanovich.handhSchoolHomework.presenter

import androidx.lifecycle.ViewModel
import ru.zdanovich.handhSchoolHomework.di.AppModule
import ru.zdanovich.handhSchoolHomework.di.DaggerViewModelInjector
import ru.zdanovich.handhSchoolHomework.di.ViewModelInjector
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository
import ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo.BridgeInfoViewModel
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(AppModule)
        .build()

    @Inject
    lateinit var repository: BridgesRepository

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is BridgesListViewModel -> injector.inject(this)
            is BridgeInfoViewModel -> injector.inject(this)
        }
    }
}