package ru.zdanovich.handhSchoolHomework.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BridgeDivorcesTimeDto(
    @SerialName("start")
    val start: String,

    @SerialName("end")
    val end: String
)