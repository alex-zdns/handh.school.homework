package ru.zdanovich.handhSchoolHomework.domain.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zdanovich.handhSchoolHomework.data.NoteAppDataBase
import ru.zdanovich.handhSchoolHomework.data.mapper.NoteMapper
import ru.zdanovich.handhSchoolHomework.domain.models.Note

class NoteRepositoryImpl(applicationContext: Context) : NoteRepository {
    private val db = NoteAppDataBase.create(applicationContext)

    override suspend fun saveNote(note: Note) = withContext(Dispatchers.IO) {
        db.notesDao.insertNote(NoteMapper.toNoteEntity(note))
    }
}