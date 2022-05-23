package com.tolulonge.footballfixtures.data.repository

import com.tolulonge.footballfixtures.core.util.ApiStatus
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.domain.mapper.AllDomainMappers
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionFixture
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionX
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow


class FootballFixturesRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val allDomainMappers: AllDomainMappers
) : FootballFixturesRepository {

    override fun getTodayFixtures(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<DomainTodayFixture>>> {
        return flow {
            emit(Resource.Loading(true))
            val localTodayFixture = localDataSource.getTodayFixturesDb()
            if (isFetchingResultFromDb(
                    fetchFromRemote,
                    localTodayFixture
                ) {
                    allDomainMappers.dataTodayFixtureToDomainTodayFixtureMapper.map(
                        localTodayFixture
                    )
                }
            ) return@flow
            emit(Resource.Loading(true))
            val response = remoteDataSource.getTodayFixtures()
            val dataTodayFixtures = retrieveContentFromRemote(response)

            updateLocalFromRemoteAndEmitResult(dataTodayFixtures, {
                localDataSource.insertTodayFixtures(it)
            },
                {
                    allDomainMappers.dataTodayFixtureToDomainTodayFixtureMapper.map(
                        localDataSource.getTodayFixturesDb()
                    )
                })
        }
    }

    override fun getCompetitionsList(fetchFromRemote: Boolean): Flow<Resource<List<DomainCompetitionX>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCompetitionX = localDataSource.getCompetitionsList()

            if (isFetchingResultFromDb(fetchFromRemote, localCompetitionX) {
                    allDomainMappers.dataCompetitionXToDomainCompetitionXMapper.map(
                        localCompetitionX
                    )
                }
            ) return@flow
            emit(Resource.Loading(true))
            val response = remoteDataSource.getCompetitionsList()
            val dataCompetitionList = retrieveContentFromRemote(response)


            updateLocalFromRemoteAndEmitResult(
                dataCompetitionList,
                { localDataSource.insertCompetitionsList(it) },
                {
                    allDomainMappers.dataCompetitionXToDomainCompetitionXMapper.map(
                        localDataSource.getCompetitionsList()
                    )
                }
            )
        }
    }

    override fun getCompetitionFixtures(
        fetchFromRemote: Boolean,
        competitionCode: String,
        matchDay: Int
    ): Flow<Resource<List<DomainCompetitionFixture>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCompetitionFixtures = localDataSource.getCompetitionFixtures(matchDay,competitionCode)

            if (isFetchingResultFromDb(
                    fetchFromRemote,
                    localCompetitionFixtures
                ) {
                    allDomainMappers.dataCompetitionFixtureToDomainCompetitionFixtureMapper.map(
                        localCompetitionFixtures
                    )
                }
            ) return@flow
            emit(Resource.Loading(true))
            val response = remoteDataSource.getCompetitionFixtures(competitionCode, matchDay)
            val dataCompetitionFixtures = retrieveContentFromRemote(response)

            updateLocalFromRemoteAndEmitResult(
                dataCompetitionFixtures,
                { localDataSource.insertCompetitionFixtures(it) },
                {
                    allDomainMappers.dataCompetitionFixtureToDomainCompetitionFixtureMapper.map(
                        localDataSource.getCompetitionFixtures(matchDay,competitionCode)
                    )
                }
            )
        }
    }

    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.isFetchingResultFromDb(
        fetchFromRemote: Boolean,
        localData: List<T>,
        retrieveFromDb: suspend () -> List<E>
    ): Boolean {

        val isDbEmpty = checkIfDatabaseIsEmpty(localData)
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            emit(
                Resource.Success(
                    data = retrieveFromDb.invoke(),
                    message = null
                )
            )
            return true
        }
        return false
    }


    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.retrieveContentFromRemote(
        response: Resource<List<T>>
    ): List<T>? {
        val result = when (response.status) {
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
        return result
    }

    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.updateLocalFromRemoteAndEmitResult(
        remoteResult: List<T>?,
        insertToDb: suspend ((List<T>) -> Unit),
        retrieveFromDb: suspend () -> List<E>
    ) {
        remoteResult?.let { results ->
            insertToDb.invoke(results)
            emit(Resource.Loading(false))
            emit(
                Resource.Success(
                    data = retrieveFromDb.invoke(),
                    message = null
                )
            )
        }
    }

    override fun <T> checkIfDatabaseIsEmpty(
        localData: List<T>
    ): Boolean {
        return localData.isEmpty()
    }


}
