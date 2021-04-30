package ru.zdanovich.handhSchoolHomework.data.db.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BridgeClosedTimeJson(
    @SerialName("start")
    val start: String,

    @SerialName("end")
    val end: String
)