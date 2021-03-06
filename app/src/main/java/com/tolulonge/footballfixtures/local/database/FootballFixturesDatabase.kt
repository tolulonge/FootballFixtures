package com.tolulonge.footballfixtures.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tolulonge.footballfixtures.local.model.LocalCompetitionFixture
import com.tolulonge.footballfixtures.local.model.LocalCompetitionX
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture

@Database(
    entities = [LocalTodayFixture::class, LocalCompetitionX::class, LocalCompetitionFixture::class],
    version = 1,
)
abstract class FootballFixturesDatabase : RoomDatabase() {

    abstract val todayFixturesDao: TodayFixturesDao

    abstract val competitionsDao: CompetitionsDao

    abstract val competitionFixturesDao: CompetitionFixturesDao


    companion object {
        const val DATABASE_NAME = "football_fixtures_db"

    }
}
