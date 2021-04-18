package ru.zdanovich.handhSchoolHomework.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.zdanovich.handhSchoolHomework.domain.models.Note

interface NoteRepository {
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    fun getAllNotes(): Flow<List<Note>>
}