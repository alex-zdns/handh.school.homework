package ru.zdanovich.handhSchoolHomework.domain.apiService

import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

interface BridgeApiService {
    suspend fun getBridges(): Map<Int, Bridge>
    suspend fun getBridge(bridgeId: Int): Bridge
}