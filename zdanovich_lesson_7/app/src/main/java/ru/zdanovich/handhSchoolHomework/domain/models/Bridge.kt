package ru.zdanovich.handhSchoolHomework.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bridge(
    val id: Int,
    val name: String,
    val description: String,
    val bridgeDivorcesTime: String,
    val photoCloseUrl: String,
    val photoOpenUrl: String,
) : Parcelable