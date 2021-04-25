package ru.zdanovich.handhSchoolHomework.presenter.helpers

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

fun getBridgeIcon(status: Bridge.BridgeStatus): Int = when (status) {
    Bridge.BridgeStatus.Close -> R.drawable.ic_bridge_late_vector
    Bridge.BridgeStatus.Open -> R.drawable.ic_bridge_normal_vector
    Bridge.BridgeStatus.SoonClose -> R.drawable.ic_bridge_soon_vector
}

fun getBridgeIconBitmap(status: Bridge.BridgeStatus): Int = when (status) {
    Bridge.BridgeStatus.Close -> R.drawable.ic_brige_late
    Bridge.BridgeStatus.Open -> R.drawable.ic_brige_normal
    Bridge.BridgeStatus.SoonClose -> R.drawable.ic_brige_soon
}