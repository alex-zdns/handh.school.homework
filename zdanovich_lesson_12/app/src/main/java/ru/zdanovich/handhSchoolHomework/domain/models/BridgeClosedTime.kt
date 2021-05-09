package ru.zdanovich.handhSchoolHomework.domain.models

data class BridgeClosedTime(
    val start: String,
    val end: String
) {
    fun toUiString(): String = "$start - $end"
}