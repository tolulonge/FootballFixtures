package com.tolulonge.footballfixtures.data.repository

import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getTodayFixturesDb(): List<DataTodayFixture>
    suspend fun insertTodayFixtures(allFixture: List<DataTodayFixture>)

    suspend fun getCompetitionsList(): List<DataCompetitionX>
    suspend fun insertCompetitionsList(allCompetitions: List<DataCompetitionX>)

    suspend fun getCompetitionFixtures(matchDay: Int,competitionCode: String): List<DataCompetitionFixture>
    suspend fun insertCompetitionFixtures(allFixture: List<DataCompetitionFixture>)

}
