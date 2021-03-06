package ru.zdanovich.handhSchoolHomework.presenter.helpers

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

fun getBridgeIcon(status: Bridge.BridgeStatus): Int = when (status) {
    Bridge.BridgeStatus.Close -> R.drawable.ic_bridge_late
    Bridge.BridgeStatus.Open -> R.drawable.ic_bridge_normal
    Bridge.BridgeStatus.SoonClose -> R.drawable.ic_bridge_soon
}