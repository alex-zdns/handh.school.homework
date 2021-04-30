package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.di.AppModule
import ru.zdanovich.handhSchoolHomework.di.DaggerViewModelInjector
import ru.zdanovich.handhSchoolHomework.di.ViewModelInjector
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository
import java.io.IOException
import javax.inject.Inject

class BridgesListViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(AppModule)
        .build()

    @Inject
    lateinit var  repository: BridgesRepository

    private val _mutableState = MutableLiveData<BridgeListState>(BridgeListState.Default)
    val state: LiveData<BridgeListState> get() = _mutableState

    init {
        injector.inject(this)
    }

    fun getBridges() {
        viewModelScope.launch {
            try {
                _mutableState.value = BridgeListState.Loading
                val bridges = repository.getBridges()
                _mutableState.value = BridgeListState.Success(bridges)
            } catch (e: IOException) {
                _mutableState.value = BridgeListState.Error.Internet
            } catch (e: Exception) {
                Log.e(this::class.simpleName, e.message ?: "")
                _mutableState.value = BridgeListState.Error.Other
            }
        }
    }
}