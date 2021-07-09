package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import java.io.IOException

class BridgesListViewModel(
    private val bridgeApi: BridgeApi
) : ViewModel() {
    private val _mutableState = MutableLiveData<BridgeListState>(BridgeListState.Default)
    val state: LiveData<BridgeListState> get() = _mutableState

    private val bag = CompositeDisposable()

    fun getBridges() {
        _mutableState.value = BridgeListState.Loading

        val disposable = bridgeApi.getBridges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                val bridges = result.map { BridgeMapper.mapBridge(it) }
                _mutableState.value = BridgeListState.Success(bridges)
            }, { e ->

                if (e is IOException) {
                    _mutableState.value = BridgeListState.Error.Internet
                } else {
                    Log.e(this::class.simpleName, e.message ?: "")
                    _mutableState.value = BridgeListState.Error.Other
                }
            })

        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }
}