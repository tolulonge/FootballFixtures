package com.tolulonge.footballfixtures.remote

import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.RemoteDataSource

class FakeRemoteDataSourceImpl: RemoteDataSource {
    override suspend fun getTodayFixtures(): Resource<List<DataTodayFixture>> {
        return Resource.Success(TestRemoteData.listOfDataTodayFixture)
    }

    override suspend fun getCompetitionsList(): Resource<List<DataCompetitionX>> {
        return Resource.Success(TestRemoteData.listOfDataCompetitionX)
    }

    override suspend fun getCompetitionFixtures(
        competitionCode: String,
        matchDay: Int
    ): Resource<List<DataCompetitionFixture>> {
        return Resource.Success(TestRemoteData.listOfDataCompetitionFixture)
    }

}