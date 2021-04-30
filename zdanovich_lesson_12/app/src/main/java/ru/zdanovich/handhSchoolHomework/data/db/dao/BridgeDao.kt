package ru.zdanovich.handhSchoolHomework.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zdanovich.handhSchoolHomework.data.db.entities.BridgeEntity

@Dao
interface BridgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBridges(bridges: List<BridgeEntity>)

    @Query("SELECT * FROM bridges")
    fun getAllBridges(): Flow<List<BridgeEntity>>

    @Query("SELECT * FROM bridges WHERE _id = :bridgeId")
    fun getBridgeById(bridgeId: Int): BridgeEntity
}