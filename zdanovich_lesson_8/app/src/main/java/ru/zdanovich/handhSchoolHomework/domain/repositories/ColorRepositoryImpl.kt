package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

class ColorRepositoryImpl : ColorRepository {
    override fun getColors(): List<NoteColor> = listOf(
        NoteColor(backgroundColor = 0xFFe51c23.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFFe91e63.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF9c27b0.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF673ab7.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF5677fc.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF02a9f4.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF00bcd4.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF009788.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF8cc34a.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFFcddc38.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFFffeb3c.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFFfec10a.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFFff5623.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF9e9e9e.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = 0xFF607d8b.toInt(), textColor = WHITE),
        NoteColor(backgroundColor = WHITE, textColor = 0xFF000000.toInt()),
        )

    companion object {
        const val WHITE = 0xFFFFFFFF.toInt()
    }
}