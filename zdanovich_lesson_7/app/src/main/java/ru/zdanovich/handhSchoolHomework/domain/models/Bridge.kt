package ru.zdanovich.handhSchoolHomework.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Parcelize
data class Bridge(
    val id: Int,
    val name: String,
    val description: String,
    val bridgeDivorcesTimes: List<BridgeClosedTime>,
    val photoCloseUrl: String,
    val photoOpenUrl: String,
) : Parcelable {
    fun getBridgeStatus(): BridgeStatus {
        val now = LocalTime.now(spbZone)
        var isSoonClosed = false

        for (closeTime in bridgeDivorcesTimes) {
            val startTime = LocalTime.parse(closeTime.start, format)
            val endTime = LocalTime.parse(closeTime.end, format)

            if (now.isAfter(startTime) && now.isBefore(endTime))
                return BridgeStatus.Close

            if (now.plusHours(ONE_HOUR).isAfter(startTime) && now.isBefore(endTime))
                isSoonClosed = true
        }

        return if (isSoonClosed) BridgeStatus.SoonClose else BridgeStatus.Open
    }

    sealed class BridgeStatus {
        object Open : BridgeStatus()
        object Close : BridgeStatus()
        object SoonClose : BridgeStatus()
    }

    companion object {
        private const val TIME_PATTERN = "H:mm"
        const val ONE_HOUR: Long = 1
        val format: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN)
        val spbZone: ZoneId = ZoneId.of("Europe/Moscow")
    }
}