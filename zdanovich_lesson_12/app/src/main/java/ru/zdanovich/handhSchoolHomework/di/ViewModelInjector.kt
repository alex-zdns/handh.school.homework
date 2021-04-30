package ru.zdanovich.handhSchoolHomework.di

import dagger.Component
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface ViewModelInjector {
    fun inject(viewModel: BridgesListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: AppModule): Builder
    }
}