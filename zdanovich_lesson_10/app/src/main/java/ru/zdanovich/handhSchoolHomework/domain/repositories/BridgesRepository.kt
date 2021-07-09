package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

interface BridgesRepository {
    suspend fun getBridges(): List<Bridge>
}