package ru.zdanovich.handhSchoolHomework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.zdanovich.handhSchoolHomework.data.db.dao.BridgeDao
import ru.zdanovich.handhSchoolHomework.data.db.entities.BridgeEntity
import ru.zdanovich.handhSchoolHomework.data.db.entities.DbContract

@Database(
    entities = [BridgeEntity::class],
    version = 1
)
abstract class BridgesDataBase: RoomDatabase() {
    abstract val bridgeDao: BridgeDao

    companion object {
        fun create(applicationContext: Context): BridgesDataBase = Room.databaseBuilder(
            applicationContext,
            BridgesDataBase::class.java,
            DbContract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}