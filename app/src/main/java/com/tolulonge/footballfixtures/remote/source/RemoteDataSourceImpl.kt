package com.tolulonge.footballfixtures.remote.source

import com.google.gson.Gson
import com.tolulonge.footballfixtures.core.util.BaseRemoteRepository
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.RemoteDataSource
import com.tolulonge.footballfixtures.remote.api.FootballFixturesApi
import com.tolulonge.footballfixtures.remote.mapper.AllRemoteMappers
import com.tolulonge.footballfixtures.remote.mapper.RemoteTodayFixtureToDataTodayFixtureMapper


class RemoteDataSourceImpl(
    private val footballFixturesApi: FootballFixturesApi,
    private val allRemoteMappers: AllRemoteMappers
) : RemoteDataSource, BaseRemoteRepository() {

    override suspend fun getTodayFixtures(): Resource<List<DataTodayFixture>> = safeApiCall{
       allRemoteMappers.remoteTodayFixtureToDataTodayFixtureMapper.map(footballFixturesApi.getTodayFixtures().matches)
    }

    override suspend fun getCompetitionsList(): Resource<List<DataCompetitionX>> = safeApiCall {
        allRemoteMappers.remoteCompetitionToDataCompetitionMapper.map(footballFixturesApi.getCompetitionsList().competitions)
    }


}
