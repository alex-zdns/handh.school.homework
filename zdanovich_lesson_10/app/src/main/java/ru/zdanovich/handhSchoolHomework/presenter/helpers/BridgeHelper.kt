package ru.zdanovich.handhSchoolHomework.presenter.helpers

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ViewHolderBridgeBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgeAdapter

fun getBridgeIcon(status: Bridge.BridgeStatus): Int = when (status) {
    Bridge.BridgeStatus.Close -> R.drawable.ic_bridge_late_vector
    Bridge.BridgeStatus.Open -> R.drawable.ic_bridge_normal_vector
    Bridge.BridgeStatus.SoonClose -> R.drawable.ic_bridge_soon_vector
}

fun ViewHolderBridgeBinding.onBind(bridge: Bridge) {
    vhbStatusIcon.setImageResource(getBridgeIcon(bridge.getBridgeStatus()))
    vhbBridgeTimeDivorces.text =
        bridge.bridgeDivorcesTimes.joinToString(postfix = BridgeAdapter.BRIDGE_DIVORCES_TIME_POSTFIX) { it.toUiString() }
    vhbBridgeName.text = bridge.name
}