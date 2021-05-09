package ru.zdanovich.handhSchoolHomework.data.apiService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import ru.zdanovich.handhSchoolHomework.domain.apiService.BridgeApiService
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import javax.inject.Inject

class BridgeApiServiceImpl @Inject constructor(private val api: BridgeApi): BridgeApiService {
    override suspend fun getBridges(): Map<Int, Bridge> = withContext(Dispatchers.IO) {
        return@withContext api.getBridges().map { BridgeMapper.mapBridge(it) }.associateBy {it.id}
    }

    override suspend fun getBridge(bridgeId: Int): Bridge = withContext(Dispatchers.IO) {
        return@withContext BridgeMapper.mapBridge(api.getBridge(bridgeId))
    }
}