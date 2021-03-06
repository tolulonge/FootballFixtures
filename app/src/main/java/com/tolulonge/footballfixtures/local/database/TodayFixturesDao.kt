package com.tolulonge.footballfixtures.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture

@Dao
interface TodayFixturesDao {

    @Query("SELECT * FROM fixtures ORDER BY date DESC")
    suspend fun getTodayFixtures(): List<LocalTodayFixture>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodayFixtures(allFixtures: List<LocalTodayFixture>)

}
