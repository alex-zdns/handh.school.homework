package ru.zdanovich.handhSchoolHomework.data.network.dto

import com.google.gson.annotations.SerializedName

data class BridgeDivorcesTimeDto(
    @SerializedName("start")
    val start: String,

    @SerializedName("end")
    val end: String
)