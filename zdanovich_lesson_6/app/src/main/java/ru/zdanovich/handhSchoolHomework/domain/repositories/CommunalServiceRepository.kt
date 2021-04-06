package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.CommunalService

interface CommunalServiceRepository {
    fun getCommunalServiceCards(): List<CommunalService>
}