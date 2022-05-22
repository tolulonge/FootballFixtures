package com.tolulonge.footballfixtures.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.*
import androidx.sqlite.db.*
import com.tolulonge.footballfixtures.local.database.TodayFixturesDao
import com.tolulonge.footballfixtures.local.model.LocalCompetitionX
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture

@Database(
    entities = [LocalTodayFixture::class,LocalCompetitionX::class],
    version = 1,
)
abstract class FootballFixturesDatabase : RoomDatabase() {

    abstract val todayFixturesDao: TodayFixturesDao

    abstract val competitionsDao: CompetitionsDao

    companion object {
        const val DATABASE_NAME = "football_fixtures_db"

    }
}
