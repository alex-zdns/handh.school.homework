package ru.zdanovich.handhSchoolHomework.domain.models

sealed class CityWeatherResult {
    class Success(val cityWeather: CityWeather) : CityWeatherResult()
    object Error : CityWeatherResult()
}