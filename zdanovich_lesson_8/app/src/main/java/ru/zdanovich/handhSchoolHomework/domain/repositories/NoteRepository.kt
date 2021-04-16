package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Note

interface NoteRepository {
    suspend fun saveNote(note: Note)
}