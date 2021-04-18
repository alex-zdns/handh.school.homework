package ru.zdanovich.handhSchoolHomework.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zdanovich.handhSchoolHomework.data.entities.NoteEntity

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE _id = :noteId")
    fun deleteNoteById(noteId: Int)

    @Query("UPDATE notes SET is_archived = 1 WHERE _id = :noteId")
    fun archiveNoteById(noteId: Int)
}