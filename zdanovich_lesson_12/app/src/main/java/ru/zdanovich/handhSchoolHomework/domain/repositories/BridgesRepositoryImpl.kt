package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.apiService.BridgeApiService
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import javax.inject.Inject

class BridgesRepositoryImpl @Inject constructor(private val apiService: BridgeApiService): BridgesRepository {
    override suspend fun getBridges(): Map<Int, Bridge> = apiService.getBridges()

    override suspend fun getBridge(bridgeId: Int): Bridge = apiService.getBridge(bridgeId)
}