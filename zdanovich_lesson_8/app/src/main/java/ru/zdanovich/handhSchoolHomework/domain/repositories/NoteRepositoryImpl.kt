package ru.zdanovich.handhSchoolHomework.domain.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.NoteAppDataBase
import ru.zdanovich.handhSchoolHomework.data.mapper.NoteMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Note

class NoteRepositoryImpl(applicationContext: Context) : NoteRepository {
    private val db = NoteAppDataBase.create(applicationContext)

    override suspend fun saveNote(note: Note) = withContext(Dispatchers.IO) {
        db.notesDao.insertNote(NoteMapper.toNoteEntity(note))
    }

    override fun getAllNotes(): Flow<List<Note>> =
        db.notesDao.getAllNotes()
            .map { list -> list.map { noteEntity -> NoteMapper.toNote(noteEntity) } }

}