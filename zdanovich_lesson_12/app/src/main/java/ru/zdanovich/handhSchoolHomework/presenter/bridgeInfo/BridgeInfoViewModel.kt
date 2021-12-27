package ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.BaseViewModel
import java.io.IOException

class BridgeInfoViewModel: BaseViewModel() {
    private val _mutableState = MutableLiveData<BridgeState>(BridgeState.Default)
    val state: LiveData<BridgeState> get() = _mutableState

    fun getBridge(bridgeId: Int) {
        viewModelScope.launch {
            try {
                _mutableState.value = BridgeState.Loading
                val bridge = repository.getBridge(bridgeId)
                _mutableState.value = BridgeState.Success(bridge)
            } catch (e: IOException) {
                _mutableState.value = BridgeState.Error.Internet
            } catch (e: Exception) {
                Log.e(this::class.simpleName, e.message ?: "")
                _mutableState.value = BridgeState.Error.Other
            }
        }
    }

    sealed class BridgeState {
        object Default : BridgeState()
        object Loading : BridgeState()

        sealed class Error : BridgeState() {
            object Internet : Error()
            object Other : Error()
        }

        class Success(val bridge: Bridge) : BridgeState()
    }
}