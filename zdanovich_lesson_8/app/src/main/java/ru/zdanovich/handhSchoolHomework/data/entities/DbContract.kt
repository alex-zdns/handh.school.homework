package ru.zdanovich.handhSchoolHomework.data.entities

import android.provider.BaseColumns

object DbContract {
    const val DATABASE_NAME = "NotesApp.db"

    object Notes {
        const val TABLE_NAME = "notes"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_BODY = "body"
        const val COLUMN_NAME_IS_ARCHIVED = "is_archived"
        const val COLUMN_NAME_BACKGROUND_COLOR = "background_color"
        const val COLUMN_NAME_TEXT_COLOR = "text_color"
    }
}