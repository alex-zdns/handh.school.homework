package ru.zdanovich.handhSchoolHomework.domain.models

data class CommunalService(
    val type: CommunalServiceType,
    val icon: Int,
    val title: String,
    val accountId: Long,
    val message: String,
    val hasDept: Boolean = false
)