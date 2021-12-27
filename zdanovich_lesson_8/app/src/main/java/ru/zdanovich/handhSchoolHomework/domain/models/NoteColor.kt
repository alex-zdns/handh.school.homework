package ru.zdanovich.handhSchoolHomework.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteColor(
    val backgroundColor: Int = WHITE,
    val textColor: Int = BLACK
): Parcelable {

    companion object {
        const val WHITE = 0xFFFFFFFF.toInt()
        const val BLACK = 0xFF000000.toInt()
    }
}