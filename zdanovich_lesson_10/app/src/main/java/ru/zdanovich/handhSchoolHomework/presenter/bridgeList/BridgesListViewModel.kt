package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import java.io.IOException

class BridgesListViewModel(
    private val bridgeApi: BridgeApi
) : ViewModel() {
    private val _mutableState = MutableLiveData<BridgeListState>(BridgeListState.Default)
    val state: LiveData<BridgeListState> get() = _mutableState

    fun getBridges() {
        viewModelScope.launch {
            try {
                _mutableState.value = BridgeListState.Loading

                val bridgesDto = bridgeApi.getBridges()
                val bridges = bridgesDto.map { BridgeMapper.mapBridge(it) }

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