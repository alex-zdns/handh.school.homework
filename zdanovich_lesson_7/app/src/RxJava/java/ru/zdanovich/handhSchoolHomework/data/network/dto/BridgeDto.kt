package ru.zdanovich.handhSchoolHomework.data.network.dto

import com.google.gson.annotations.SerializedName

data class BridgeDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("divorces")
    val bridgeDivorcesTime: List<BridgeDivorcesTimeDto>,

    @SerializedName("photo_close_url")
    val photoCloseUrl: String,

    @SerializedName("photo_open_url")
    val photoOpenUrl: String,
)