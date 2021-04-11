package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

class BridgesListViewModel(
    private val bridgeApi: BridgeApi
) : ViewModel() {
    private val _mutableState = MutableLiveData<State>(State.Default)
    val state: LiveData<State> get() = _mutableState

    private val bag = CompositeDisposable()

    fun getBridges() {
        _mutableState.value = State.Loading

        val disposable = bridgeApi.getBridges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result ->
                val bridges = result.map { BridgeMapper.mapBridge(it) }
                _mutableState.value = State.Success(bridges)
            }, {
                _mutableState.value = State.Error
            })

        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }

    sealed class State {
        object Default : State()
        object Loading : State()
        object Error : State()
        class Success(val bridges: List<Bridge>) : State()
    }
}