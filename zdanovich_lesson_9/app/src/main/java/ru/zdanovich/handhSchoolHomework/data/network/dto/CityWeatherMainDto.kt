package ru.zdanovich.handhSchoolHomework.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherMainDto(
    @SerialName("temp")
    val temp: Double,

    @SerialName("feels_like")
    val feelsLikeTemp: Double
)