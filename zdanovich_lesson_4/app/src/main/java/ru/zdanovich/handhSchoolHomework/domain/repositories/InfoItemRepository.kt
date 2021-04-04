package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.BaseInfoItem

interface InfoItemRepository {
    fun getInfoItem(): List<BaseInfoItem>
}