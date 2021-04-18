package ru.zdanovich.handhSchoolHomework.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zdanovich.handhSchoolHomework.data.entities.DbContract
import ru.zdanovich.handhSchoolHomework.data.entities.NoteEntity

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM ${DbContract.Notes.TABLE_NAME}")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM ${DbContract.Notes.TABLE_NAME} WHERE _id = :noteId")
    fun deleteNoteById(noteId: Int)
}