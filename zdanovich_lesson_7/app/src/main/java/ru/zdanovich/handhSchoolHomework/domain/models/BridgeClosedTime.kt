package ru.zdanovich.handhSchoolHomework.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BridgeClosedTime(
    val start: String,
    val end: String
) : Parcelable {
    fun toUiString(): String = "$start - $end"
}