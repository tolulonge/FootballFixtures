package com.tolulonge.footballfixtures.local

import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.LocalDataSource
import com.tolulonge.footballfixtures.local.mapper.AllLocalMappers

class FakeLocalDataSourceImpl(
    private val allLocalMappers: AllLocalMappers,
    private val fakeDatabase: FakeDatabase
): LocalDataSource {

    override suspend fun getTodayFixturesDb(): List<DataTodayFixture> =
        allLocalMappers.localDataTodayFixtureListMapper.to(fakeDatabase.listOfLocalTodayFixture.sortedByDescending { it.date })

    override suspend fun insertTodayFixtures(allFixture: List<DataTodayFixture>) {
        fakeDatabase.listOfLocalTodayFixture.addAll(allLocalMappers.localDataTodayFixtureListMapper.from(allFixture).toMutableList())
    }

    override suspend fun getCompetitionsList(): List<DataCompetitionX> =
        allLocalMappers.localDataCompetitionXListMapper.to(fakeDatabase.listOfLocalCompetitionX.toList())

    override suspend fun insertCompetitionsList(allCompetitions: List<DataCompetitionX>) {
        fakeDatabase.listOfLocalCompetitionX.addAll(allLocalMappers.localDataCompetitionXListMapper.from(allCompetitions).toMutableList())
    }

    override suspend fun getCompetitionFixtures(
        matchDay: Int,
        competitionCode: String
    ): List<DataCompetitionFixture> =
        allLocalMappers.localDataCompetitionFixtureListMapper.to(fakeDatabase.listOfLocalCompetitionFixture.filter {
            it.competitionCode == competitionCode && it.matchDay == matchDay
        }).sortedByDescending { it.date }

    override suspend fun insertCompetitionFixtures(allFixture: List<DataCompetitionFixture>) {
        fakeDatabase.listOfLocalCompetitionFixture.addAll(allLocalMappers.localDataCompetitionFixtureListMapper.from(allFixture).toMutableList())
    }

}