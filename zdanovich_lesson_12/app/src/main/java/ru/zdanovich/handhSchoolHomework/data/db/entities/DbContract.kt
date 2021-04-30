package ru.zdanovich.handhSchoolHomework.data.db.entities

import android.provider.BaseColumns

object DbContract {
    const val DATABASE_NAME = "BridgeApp.db"

    object Bridges {
        const val TABLE_NAME = "bridges"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_BRIDGE_DIVORCES_TIMES = "bridge_divorces_times"
        const val COLUMN_NAME_PHOTO_CLOSE_URL = "photo_close_url"
        const val COLUMN_NAME_PHOTO_OPEN_URL = "photo_open_url"
        const val COLUMN_NAME_LATITUDE = "latitude"
        const val COLUMN_NAME_LONGITUDE = "longitude"
    }
}