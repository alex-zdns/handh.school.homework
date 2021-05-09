package ru.zdanovich.handhSchoolHomework.domain.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.network.WeatherApi
import ru.zdanovich.handhSchoolHomework.data.network.mappers.CityWeatherDtoMapper
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeatherResult

class CityWeatherRepositoryImpl(private val api: WeatherApi) : CityWeatherRepository {
    override suspend fun getCityWeatherRepository(): CityWeatherResult =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val weatherDto = api.getWeather()
                val weather = CityWeatherDtoMapper.mapCityWeather(weatherDto)
                CityWeatherResult.Success(weather)
            } catch (e: Exception) {
                CityWeatherResult.Error
            }
        }
}