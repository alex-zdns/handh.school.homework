package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

interface BridgeRepository {
    suspend fun getBridges(): List<Bridge>
}