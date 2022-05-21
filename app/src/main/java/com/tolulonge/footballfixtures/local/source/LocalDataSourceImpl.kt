package com.tolulonge.footballfixtures.local.source

import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.LocalDataSource
import com.tolulonge.footballfixtures.local.database.TodayFixturesDao
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper


class LocalDataSourceImpl(
    private val localDataTodayFixtureListMapper: LocalDataTodayFixtureListMapper,
    private val fixturesDao: TodayFixturesDao
) : LocalDataSource {
    override suspend fun getTodayFixturesDb(): List<DataTodayFixture> =
        localDataTodayFixtureListMapper.to(fixturesDao.getTodayFixtures())

    override suspend fun insertTodayFixtures(allFixture: List<DataTodayFixture>) {
        fixturesDao.insertTodayFixtures(localDataTodayFixtureListMapper.from(allFixture))
    }

}
