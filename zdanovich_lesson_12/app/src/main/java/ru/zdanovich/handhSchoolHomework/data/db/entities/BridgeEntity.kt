package ru.zdanovich.handhSchoolHomework.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(
    tableName = DbContract.Bridges.TABLE_NAME,
    indices = [Index(DbContract.Bridges.COLUMN_NAME_ID)]
)
data class BridgeEntity(
    @PrimaryKey
    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_ID)
    val id: Int,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_NAME)
    val name: String,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_DESCRIPTION)
    val description: String,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_BRIDGE_DIVORCES_TIMES)
    val bridgeDivorcesTimes: String,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_PHOTO_CLOSE_URL)
    val photoCloseUrl: String,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_PHOTO_OPEN_URL)
    val photoOpenUrl: String,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_LATITUDE)
    @SerialName("lat")
    val latitude: Double,

    @ColumnInfo(name = DbContract.Bridges.COLUMN_NAME_LONGITUDE)
    @SerialName("lng")
    val longitude: Double
)