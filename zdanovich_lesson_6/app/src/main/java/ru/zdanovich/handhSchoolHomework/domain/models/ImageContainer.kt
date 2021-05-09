package ru.zdanovich.handhSchoolHomework.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageContainer(
    val id: Int,
    val image: Int
) : Parcelable