package com.tolulonge.footballfixtures.data.repository

import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getTodayFixturesDb(): List<DataTodayFixture>
    suspend fun insertTodayFixtures(allFixture: List<DataTodayFixture>)

}
