package ru.zdanovich.handhSchoolHomework.data.entities

import android.provider.BaseColumns

object DbContract {
    const val DATABASE_NAME = "NotesApp.db"

    object Notes {
        const val TABLE_NAME = "notes"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_BODY = "body"
    }
}