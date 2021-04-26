package ru.zdanovich.handhSchoolHomework.domain.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

class BridgeRepositoryImpl(private val api: BridgeApi): BridgeRepository {
    override suspend fun getBridges(): List<Bridge> = withContext(Dispatchers.IO) {
        return@withContext api.getBridges().map { BridgeMapper.mapBridge(it) }
    }
}