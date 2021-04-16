package ru.zdanovich.handhSchoolHomework.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.Notes.TABLE_NAME,
    indices = [Index(DbContract.Notes.COLUMN_NAME_ID)]
)
data class NoteEntity(
    @ColumnInfo(name = DbContract.Notes.COLUMN_NAME_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = DbContract.Notes.COLUMN_NAME_TITLE)
    val title: String,

    @ColumnInfo(name = DbContract.Notes.COLUMN_NAME_BODY)
    val body: String
)