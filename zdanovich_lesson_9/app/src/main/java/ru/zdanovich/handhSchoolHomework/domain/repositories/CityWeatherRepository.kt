package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.CityWeatherResult

interface CityWeatherRepository {
    suspend fun getCityWeatherRepository(): CityWeatherResult
}