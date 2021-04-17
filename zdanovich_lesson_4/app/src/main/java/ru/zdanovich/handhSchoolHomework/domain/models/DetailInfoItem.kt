package ru.zdanovich.handhSchoolHomework.domain.models

data class DetailInfoItem(
    override val icon: Int,
    override val title: String,
    val message: String,
    val hasDebt: Boolean = false
) : BaseInfoItem(icon, title)