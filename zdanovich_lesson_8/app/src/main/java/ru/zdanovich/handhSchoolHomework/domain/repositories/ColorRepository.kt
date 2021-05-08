package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

interface ColorRepository {
    fun getColors(): List<NoteColor>
}