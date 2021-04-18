package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

class ColorRepositoryImpl : ColorRepository {
    override fun getColors(): List<NoteColor> = listOf(
        NoteColor(backgroundColor = 0xe51c23, textColor = WHITE),
        NoteColor(backgroundColor = 0xe91e63, textColor = WHITE),
        NoteColor(backgroundColor = 0x9c27b0, textColor = WHITE),
        NoteColor(backgroundColor = 0x673ab7, textColor = WHITE),
        NoteColor(backgroundColor = 0x5677fc, textColor = WHITE),
        NoteColor(backgroundColor = 0x02a9f4, textColor = WHITE),
        NoteColor(backgroundColor = 0x00bcd4, textColor = WHITE),
        NoteColor(backgroundColor = 0x009788, textColor = WHITE),
        NoteColor(backgroundColor = 0x8cc34a, textColor = WHITE),
        NoteColor(backgroundColor = 0xcddc38, textColor = WHITE),
        NoteColor(backgroundColor = 0xffeb3c, textColor = WHITE),
        NoteColor(backgroundColor = 0xfec10a, textColor = WHITE),
        NoteColor(backgroundColor = 0xff5623, textColor = WHITE),
        NoteColor(backgroundColor = 0x9e9e9e, textColor = WHITE),
        NoteColor(backgroundColor = 0x607d8b, textColor = WHITE),
        NoteColor(backgroundColor = WHITE, textColor = 0x000000),
        )

    companion object {
        const val WHITE = 0xFFFFFF
    }
}