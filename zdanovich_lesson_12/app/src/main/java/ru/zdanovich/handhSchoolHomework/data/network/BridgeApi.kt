package ru.zdanovich.handhSchoolHomework.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.zdanovich.handhSchoolHomework.data.network.dto.BridgeDto

interface BridgeApi {
    @GET("bridges")
    suspend fun getBridges(): List<BridgeDto>

    @GET("bridges/{bridge_id}")
    suspend fun getBridge(@Path("bridge_id") bridgeId: Int): BridgeDto
}