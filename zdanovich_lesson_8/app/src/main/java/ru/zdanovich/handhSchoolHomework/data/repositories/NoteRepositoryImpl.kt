package ru.zdanovich.handhSchoolHomework.data.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.NoteAppDataBase
import ru.zdanovich.handhSchoolHomework.data.mapper.NoteMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.domain.repositories.NoteRepository

class NoteRepositoryImpl(applicationContext: Context) : NoteRepository {
    private val db = NoteAppDataBase.create(applicationContext)

    override suspend fun saveNote(note: Note) = withContext(Dispatchers.IO) {
        db.notesDao.insertNote(NoteMapper.toNoteEntity(note))
    }

    override suspend fun deleteNote(noteId: Int) = withContext(Dispatchers.IO) {
        db.notesDao.deleteNoteById(noteId)
    }

    override fun getAllNotes(): Flow<List<Note>> =
        db.notesDao.getAllNotes()
            .map { list -> list.filter { noteEntity -> !noteEntity.isArchived } }
            .map { list -> list.map { noteEntity -> NoteMapper.toNote(noteEntity) } }

    override suspend fun archiveNote(noteId: Int) = withContext(Dispatchers.IO) {
        db.notesDao.archiveNoteById(noteId)
    }

    override suspend fun searchNotes(query: String): List<Note> = withContext(Dispatchers.IO) {
        db.notesDao.searchNote("%$query%")
            .map { noteEntity -> NoteMapper.toNote(noteEntity) }
    }
}