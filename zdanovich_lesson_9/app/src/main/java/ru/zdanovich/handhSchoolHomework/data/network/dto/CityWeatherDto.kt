package ru.zdanovich.handhSchoolHomework.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDto(
    @SerialName("main")
    val main: CityWeatherMainDto,

    @SerialName("name")
    val city: String
)