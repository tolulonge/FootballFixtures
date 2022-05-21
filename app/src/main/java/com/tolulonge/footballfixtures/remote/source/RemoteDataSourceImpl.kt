package com.tolulonge.footballfixtures.remote.source

import com.google.gson.Gson
import com.tolulonge.footballfixtures.core.util.BaseRemoteRepository
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.data.repository.RemoteDataSource
import com.tolulonge.footballfixtures.remote.api.FootballFixturesApi
import com.tolulonge.footballfixtures.remote.mapper.RemoteTodayFixtureToDataTodayFixtureMapper


class RemoteDataSourceImpl(
    private val footballFixturesApi: FootballFixturesApi,
    private val remoteTodayFixtureToDataTodayFixtureMapper: RemoteTodayFixtureToDataTodayFixtureMapper
) : RemoteDataSource, BaseRemoteRepository() {

    override suspend fun getTodayFixtures(): Resource<List<DataTodayFixture>> = safeApiCall{
       remoteTodayFixtureToDataTodayFixtureMapper.map(footballFixturesApi.getTodayFixtures().matches)
    }



}
