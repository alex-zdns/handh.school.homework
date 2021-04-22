package ru.zdanovich.handhSchoolHomework.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.zdanovich.handhSchoolHomework.data.network.dto.CityWeatherDto

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String = "novosibirsk",
        @Query("units") units: String = "metric"
    ): CityWeatherDto
}