package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

class BridgeListViewModel(
    private val bridgeApi: BridgeApi
) : ViewModel() {
    private val _mutableState = MutableLiveData<State>(State.Default)
    val state: LiveData<State> get() = _mutableState

    fun getBridges() {
        viewModelScope.launch {
            try {
                _mutableState.value = State.Loading

                val bridgesDto = bridgeApi.getBridges()
                val bridges = bridgesDto.map { BridgeMapper.mapBridge(it) }

                _mutableState.value = State.Success(bridges)
            } catch (e: Exception) {
                _mutableState.value = State.Error
            }
        }
    }

    sealed class State {
        object Default : State()
        object Loading : State()
        object Error : State()
        class Success(public val bridges: List<Bridge>) : State()
    }
}