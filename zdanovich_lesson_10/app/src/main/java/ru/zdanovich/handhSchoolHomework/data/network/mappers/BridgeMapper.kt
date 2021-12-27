package ru.zdanovich.handhSchoolHomework.data.network.mappers

import ru.zdanovich.handhSchoolHomework.data.network.dto.BridgeDto
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.domain.models.BridgeClosedTime
import ru.zdanovich.handhSchoolHomework.domain.models.Coordinate

object BridgeMapper {
    fun mapBridge(bridgeDto: BridgeDto): Bridge = Bridge(
        id = bridgeDto.id,
        name = bridgeDto.name,
        description = bridgeDto.description,
        bridgeDivorcesTimes = bridgeDto.bridgeDivorcesTime.map {
            BridgeClosedTime(it.start, it.end)
        },
        photoOpenUrl = bridgeDto.photoOpenUrl,
        photoCloseUrl = bridgeDto.photoCloseUrl,
        coordinate = Coordinate(bridgeDto.latitude, bridgeDto.longitude)
    )
}