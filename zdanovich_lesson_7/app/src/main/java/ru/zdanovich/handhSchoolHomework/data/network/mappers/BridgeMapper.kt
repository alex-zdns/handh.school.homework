package ru.zdanovich.handhSchoolHomework.data.network.mappers

import ru.zdanovich.handhSchoolHomework.data.network.dto.BridgeDto
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

object BridgeMapper {
    fun mapBridge(bridgeDto: BridgeDto): Bridge = Bridge(
        id = bridgeDto.id,
        name = bridgeDto.name,
        description = bridgeDto.description,
        bridgeDivorcesTime = bridgeDto.bridgeDivorcesTime.map { "${it.start} - ${it.end}" }
            .joinToString { it },
        photoOpenUrl = bridgeDto.photoOpenUrl,
        photoCloseUrl = bridgeDto.photoCloseUrl
    )
}