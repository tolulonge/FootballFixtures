package com.tolulonge.footballfixtures.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture
import kotlinx.coroutines.flow.Flow

@Dao
interface TodayFixturesDao {

    @Query("SELECT * FROM fixtures")
    suspend fun getTodayFixtures(): List<LocalTodayFixture>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodayFixtures(allFixtures: List<LocalTodayFixture>)

}
