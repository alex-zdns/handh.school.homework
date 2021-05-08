package ru.zdanovich.handhSchoolHomework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.zdanovich.handhSchoolHomework.data.dao.NotesDao
import ru.zdanovich.handhSchoolHomework.data.entities.DbContract
import ru.zdanovich.handhSchoolHomework.data.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteAppDataBase : RoomDatabase() {
    abstract val notesDao: NotesDao

    companion object {
        fun create(applicationContext: Context): NoteAppDataBase = Room.databaseBuilder(
            applicationContext,
            NoteAppDataBase::class.java,
            DbContract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}