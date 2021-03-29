package ru.zdanovich.handhSchoolHomework.models

data class DetailInfoItem(
    val icon: Int,
    val title: String,
    val message: String,
    val hasDebt: Boolean = false
) : InfoItem