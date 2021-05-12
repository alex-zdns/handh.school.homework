package ru.zdanovich.handhSchoolHomework.data.network.apiService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.network.WeatherApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.CityWeatherDtoMapper
import ru.zdanovich.handhSchoolHomework.domain.apiService.WeatherApiService
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeatherResult

class WeatherApiServiceImpl(private val api: WeatherApi) : WeatherApiService {
    override suspend fun getCityWeather(): CityWeatherResult =
        withContext(Dispatchers.IO)
        {
            return@withContext try {
                val weatherDto = api.getWeather()
                val weather = CityWeatherDtoMapper.mapCityWeather(weatherDto)
                CityWeatherResult.Success(weather)
            } catch (e: Exception) {
                CityWeatherResult.Error
            }
        }

}