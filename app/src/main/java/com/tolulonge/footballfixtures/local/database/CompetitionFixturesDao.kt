package com.tolulonge.footballfixtures.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.footballfixtures.local.model.LocalCompetitionFixture

@Dao
interface CompetitionFixturesDao {

    @Query("SELECT * FROM competition_fixture WHERE matchDay = :matchDay AND competitionCode = :competitionCode ORDER BY date DESC")
    suspend fun getCompetitionFixtures(matchDay: Int,competitionCode: String): List<LocalCompetitionFixture>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionFixtures(allFixtures: List<LocalCompetitionFixture>)

}
