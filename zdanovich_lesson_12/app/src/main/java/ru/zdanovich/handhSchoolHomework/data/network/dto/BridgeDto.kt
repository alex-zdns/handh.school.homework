package ru.zdanovich.handhSchoolHomework.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BridgeDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("divorces")
    val bridgeDivorcesTime: List<BridgeDivorcesTimeDto>?,

    @SerialName("photo_close_url")
    val photoCloseUrl: String,

    @SerialName("photo_open_url")
    val photoOpenUrl: String,

    @SerialName("lat")
    val latitude: Double,

    @SerialName("lng")
    val longitude: Double
)