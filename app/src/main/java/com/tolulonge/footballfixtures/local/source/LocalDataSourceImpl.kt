package com.tolulonge.footballfixtures.local.source

import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.LocalDataSource
import com.tolulonge.footballfixtures.local.database.CompetitionsDao
import com.tolulonge.footballfixtures.local.database.TodayFixturesDao
import com.tolulonge.footballfixtures.local.mapper.AllLocalMappers
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper


class LocalDataSourceImpl(
    private val allLocalMappers: AllLocalMappers,
    private val fixturesDao: TodayFixturesDao,
    private val competitionsDao: CompetitionsDao
) : LocalDataSource {
    override suspend fun getTodayFixturesDb(): List<DataTodayFixture> =
        allLocalMappers.localDataTodayFixtureListMapper.to(fixturesDao.getTodayFixtures())

    override suspend fun insertTodayFixtures(allFixture: List<DataTodayFixture>) {
        fixturesDao.insertTodayFixtures(allLocalMappers.localDataTodayFixtureListMapper.from(allFixture))
    }

    override suspend fun getCompetitionsList(): List<DataCompetitionX> =
        allLocalMappers.localDataCompetitionXListMapper.to(competitionsDao.getCompetitionsList())


    override suspend fun insertCompetitionsList(allCompetitions: List<DataCompetitionX>) {
        competitionsDao.insertCompetitionsList(allLocalMappers.localDataCompetitionXListMapper.from(allCompetitions))
    }

}
