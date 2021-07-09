package ru.zdanovich.handhSchoolHomework.data.network

import retrofit2.http.GET
import ru.zdanovich.handhSchoolHomework.data.network.dto.BridgeDto

interface BridgeApi {
    @GET("bridges")
    suspend fun getBridges(): List<BridgeDto>
}