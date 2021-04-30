package ru.zdanovich.handhSchoolHomework.data.db.mappers

import kotlinx.serialization.json.Json
import ru.zdanovich.handhSchoolHomework.data.db.entities.BridgeClosedTimeJson
import ru.zdanovich.handhSchoolHomework.data.db.entities.BridgeEntity
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.domain.models.BridgeClosedTime
import ru.zdanovich.handhSchoolHomework.domain.models.Coordinate

object BridgeMapper {
    fun toBridgeEntity(bridge: Bridge): BridgeEntity {
        val bridgeDivorcesTimesJsonString = bridge.bridgeDivorcesTimes.map {
            val bridgeClosedTimeJson = BridgeClosedTimeJsonMapper.toBridgeClosedTimeJsonMapper(it)
            Json.encodeToString(BridgeClosedTimeJson.serializer(), bridgeClosedTimeJson)
        }.joinToString(separator = SEPARATOR) { it }

        return BridgeEntity(
            id = bridge.id,
            name = bridge.name,
            description = bridge.description,
            bridgeDivorcesTimes = bridgeDivorcesTimesJsonString,
            photoCloseUrl = bridge.photoCloseUrl,
            photoOpenUrl = bridge.photoOpenUrl,
            latitude = bridge.coordinate.latitude,
            longitude = bridge.coordinate.longitude
        )
    }

    fun toBridgeEntity(bridgeEntity: BridgeEntity): Bridge {
        val bridgeDivorcesTimes =
            bridgeEntity.bridgeDivorcesTimes.split(
                SEPARATOR
            ).map {
                Json.decodeFromString(BridgeClosedTimeJson.serializer(), it)
            }.map {
                BridgeClosedTimeJsonMapper.toBridgeClosedTime(it)
            }

        return Bridge(
            id = bridgeEntity.id,
            name = bridgeEntity.name,
            description = bridgeEntity.description,
            bridgeDivorcesTimes = bridgeDivorcesTimes,
            photoCloseUrl = bridgeEntity.photoCloseUrl,
            photoOpenUrl = bridgeEntity.photoOpenUrl,
            coordinate = Coordinate(
                latitude = bridgeEntity.latitude,
                longitude = bridgeEntity.longitude
            )
        )
    }


    object BridgeClosedTimeJsonMapper {
        fun toBridgeClosedTimeJsonMapper(divorcesTimes: BridgeClosedTime): BridgeClosedTimeJson =
            BridgeClosedTimeJson(
                start = divorcesTimes.start,
                end = divorcesTimes.end
            )

        fun toBridgeClosedTime(divorcesTimesJson: BridgeClosedTimeJson): BridgeClosedTime =
            BridgeClosedTime(
                start = divorcesTimesJson.start,
                end = divorcesTimesJson.end
            )
    }

    private const val SEPARATOR = ","
}