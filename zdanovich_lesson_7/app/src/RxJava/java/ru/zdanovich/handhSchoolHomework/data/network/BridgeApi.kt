package ru.zdanovich.handhSchoolHomework.data.network

import io.reactivex.Single
import retrofit2.http.GET
import ru.zdanovich.handhSchoolHomework.data.network.dto.BridgeDto

interface BridgeApi {
    @GET("bridges")
    fun getBridges(): Single<List<BridgeDto>>
}