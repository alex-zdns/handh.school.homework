package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

sealed class BridgeListState {
    object Default : BridgeListState()
    object Loading : BridgeListState()

    sealed class Error : BridgeListState() {
        object Internet : Error()
        object Other : Error()
    }

    class Success(val bridges: List<Bridge>) : BridgeListState()
}