package com.tolulonge.footballfixtures.data.repository

import android.util.Log
import com.tolulonge.footballfixtures.core.util.ApiStatus
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.domain.mapper.DataTodayFixtureToDomainTodayFixtureMapper
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class FootballFixturesRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dataTodayFixtureToDomainTodayFixtureMapper: DataTodayFixtureToDomainTodayFixtureMapper
) : FootballFixturesRepository {

    override fun getTodayFixtures(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<DomainTodayFixture>>> {
        return flow {
            emit(Resource.Loading(true))
            val localTodayFixture = localDataSource.getTodayFixturesDb()

            val isDbEmpty = localTodayFixture.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                emit(Resource.Success(
                    data =   dataTodayFixtureToDomainTodayFixtureMapper.map(localTodayFixture),
                    message = ""
                ))
                return@flow
            }
            emit(Resource.Loading(true))
            val response = remoteDataSource.getTodayFixtures()
            val dataTodayFixtures  =  when(response.status) {
                ApiStatus.SUCCESS -> {
                  response.data
                }
                ApiStatus.ERROR -> {
                    response.message?.let {
                        emit(Resource.Error(it))
                    } ?: emit(Resource.Error("An unknown error occurred"))
                    null
                }
                ApiStatus.LOADING -> {

                    null
                }
            }



            dataTodayFixtures?.let { fixtures ->
                localDataSource.insertTodayFixtures(fixtures)
                emit(Resource.Loading(false))
                emit(Resource.Success(
                    data = dataTodayFixtureToDomainTodayFixtureMapper.map(localDataSource.getTodayFixturesDb()),
                    message = "Fixtures Successfully Refreshed"
                ))
            }
        }
    }



}
