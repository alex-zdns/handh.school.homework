package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.CityWeather

interface CityWeatherRepository {
    suspend fun getCityWeatherRepository(): CityWeatherResult

    sealed class CityWeatherResult {
        class Success(val cityWeather: CityWeather) : CityWeatherResult()
        class Error() : CityWeatherResult()
    }
}