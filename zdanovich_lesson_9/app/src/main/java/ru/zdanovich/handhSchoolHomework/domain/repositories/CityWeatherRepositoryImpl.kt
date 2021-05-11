package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.apiService.WeatherApiService
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeatherResult

class CityWeatherRepositoryImpl(private val apiService: WeatherApiService) : CityWeatherRepository {
    override suspend fun getCityWeather(): CityWeatherResult = apiService.getCityWeather()

}