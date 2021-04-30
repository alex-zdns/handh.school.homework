package ru.zdanovich.handhSchoolHomework.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.BridgesApp
import ru.zdanovich.handhSchoolHomework.data.db.BridgesDataBase
import ru.zdanovich.handhSchoolHomework.data.network.BridgeApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.BridgeMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.domain.repositories.BridgesRepository
import javax.inject.Inject

class BridgesRepositoryImpl @Inject constructor(private val api: BridgeApi) : BridgesRepository {

    private val db = BridgesDataBase.create(BridgesApp.context())

    override suspend fun getBridges(): List<Bridge> = withContext(Dispatchers.IO) {
        return@withContext api.getBridges().map { BridgeMapper.mapBridge(it) }
    }
}