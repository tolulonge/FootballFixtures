package com.tolulonge.footballfixtures.data.repository

import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture


interface RemoteDataSource {

    suspend fun getTodayFixtures(): Resource<List<DataTodayFixture>>

    suspend fun getCompetitionsList(): Resource<List<DataCompetitionX>>

    suspend fun getCompetitionFixtures(competitionCode: String, matchDay: Int): Resource<List<DataCompetitionFixture>>

}
