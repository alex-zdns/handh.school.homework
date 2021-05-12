package ru.zdanovich.handhSchoolHomework.domain.apiService

import ru.zdanovich.handhSchoolHomework.domain.models.CityWeatherResult

interface WeatherApiService {
    suspend fun getCityWeather(): CityWeatherResult
}