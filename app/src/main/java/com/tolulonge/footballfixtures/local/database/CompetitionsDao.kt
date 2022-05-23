package com.tolulonge.footballfixtures.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolulonge.footballfixtures.local.model.LocalCompetitionX

@Dao
interface CompetitionsDao {

    @Query("SELECT * FROM competitions ")
    suspend fun getCompetitionsList(): List<LocalCompetitionX>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionsList(allFixtures: List<LocalCompetitionX>)

}
