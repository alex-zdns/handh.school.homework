package ru.zdanovich.handhSchoolHomework.data.network.mappers

import ru.zdanovich.handhSchoolHomework.data.network.dto.CityWeatherDto
import ru.zdanovich.handhSchoolHomework.domain.models.CityWeather

object CityWeatherDtoMapper {
    fun mapCityWeather(dto: CityWeatherDto): CityWeather = CityWeather(
        cityName = dto.city,
        temp = dto.main.temp,
        feelsLikeTemp = dto.main.feelsLikeTemp
    )
}