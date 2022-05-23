package com.tolulonge.footballfixtures.data.repository

import com.google.common.truth.Truth.assertThat
import com.tolulonge.footballfixtures.core.util.ApiStatus
import com.tolulonge.footballfixtures.domain.mapper.AllDomainMappers
import com.tolulonge.footballfixtures.domain.mapper.DataCompetitionFixtureToDomainCompetitionFixtureMapper
import com.tolulonge.footballfixtures.domain.mapper.DataCompetitionXToDomainCompetitionXMapper
import com.tolulonge.footballfixtures.domain.mapper.DataTodayFixtureToDomainTodayFixtureMapper
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import com.tolulonge.footballfixtures.local.FakeDatabase
import com.tolulonge.footballfixtures.local.FakeLocalDataSourceImpl
import com.tolulonge.footballfixtures.local.mapper.AllLocalMappers
import com.tolulonge.footballfixtures.local.mapper.LocalDataCompetitionFixtureListMapper
import com.tolulonge.footballfixtures.local.mapper.LocalDataCompetitionXListMapper
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper
import com.tolulonge.footballfixtures.remote.FakeRemoteDataSourceImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class FootballFixturesRepositoryImplTest {

    private lateinit var repository: FootballFixturesRepository
    private lateinit var allDomainMappers: AllDomainMappers
    private lateinit var allLocalMappers: AllLocalMappers
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setUp() {
        allDomainMappers = AllDomainMappers(
            dataCompetitionXToDomainCompetitionXMapper = DataCompetitionXToDomainCompetitionXMapper(),
            dataTodayFixtureToDomainTodayFixtureMapper = DataTodayFixtureToDomainTodayFixtureMapper(),
            dataCompetitionFixtureToDomainCompetitionFixtureMapper = DataCompetitionFixtureToDomainCompetitionFixtureMapper()
        )
        allLocalMappers = AllLocalMappers(
            localDataCompetitionFixtureListMapper = LocalDataCompetitionFixtureListMapper(),
            localDataCompetitionXListMapper = LocalDataCompetitionXListMapper(),
            localDataTodayFixtureListMapper = LocalDataTodayFixtureListMapper()
        )
        localDataSource = FakeLocalDataSourceImpl(
            allLocalMappers = allLocalMappers,
            fakeDatabase = FakeDatabase()
        )
        remoteDataSource = FakeRemoteDataSourceImpl()
        repository = FootballFixturesRepositoryImpl(
            localDataSource =  localDataSource,
            remoteDataSource = remoteDataSource,
            allDomainMappers = allDomainMappers
            )

    }


    @Test
    fun `Check database contents, not empty database, return false`(){
        runBlocking {
            val localData = localDataSource.getTodayFixturesDb()
            assertThat(repository.checkIfDatabaseIsEmpty(localData)).isFalse()
        }
    }

    @Test
    fun `Check contents coming from database, not empty database and not coming from remote, return true`(){
        runBlocking {
            val localData = localDataSource.getTodayFixturesDb()
            val isDbEmpty = repository.checkIfDatabaseIsEmpty(localData)
            val fetchFromRemote = false
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            println(!isDbEmpty)
            println(!fetchFromRemote)
            assertThat(shouldLoadFromCache).isTrue()
        }
    }

    @Test
    fun `Check contents are coming from remote, empty database or fetching from remote, return true`(){
        runBlocking {
            val localData = localDataSource.getTodayFixturesDb()
            val isDbEmpty = localData.isEmpty()
            val fetchFromRemote = true
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            assertThat(shouldLoadFromCache).isFalse()
            val remoteData = remoteDataSource.getTodayFixtures()
            val dataTodayFixtures = when(remoteData.status){
                ApiStatus.SUCCESS -> {
                    remoteData.data
                }
                ApiStatus.ERROR -> {
                    null
                }
                else -> {
                    null
                }
            }
            assertThat(dataTodayFixtures).isNotNull()
        }
    }

    @Test
    fun `fetch today fixtures from remote,update database, should emit result`(){
        runBlocking {
            val response = repository.getTodayFixtures(true)
            assertThat(response.first().status).isEqualTo(ApiStatus.LOADING)
            assertThat(response.last().status).isEqualTo(ApiStatus.SUCCESS)
            assertThat(response.last().data?.get(0)?.homeTeamName).isEqualTo("Besiktas")
        }
    }

    @Test
    fun `fetch competitions list from remote,update database, should emit result`(){
        runBlocking {
            val response = repository.getCompetitionsList(true)
            assertThat(response.first().status).isEqualTo(ApiStatus.LOADING)
            assertThat(response.last().status).isEqualTo(ApiStatus.SUCCESS)
            assertThat(response.last().data?.size).isEqualTo(6)
        }
    }

    @Test
    fun `fetch competition fixture from database, given competition code and matchday, should return all possible fixtures for combination`(){
        runBlocking {
            val competitionCode = "BL1"
            val matchDay = 38
            val response = repository.getCompetitionFixtures(false, competitionCode, matchDay)
            assertThat(response.first().status).isEqualTo(ApiStatus.LOADING)
            assertThat(response.last().data?.size).isEqualTo(2)
            assertThat(response.last().data?.get(0)?.homeTeamName).isEqualTo("Bayern Munich")


        }
    }

    @Test
    fun `fetch competitions fixture list from remote, update database, should emit result`(){
        runBlocking {
            val competitionCode = "PL"
            val matchDay = 38
            val response = repository.getCompetitionFixtures(true,competitionCode, matchDay)
            assertThat(response.first().status).isEqualTo(ApiStatus.LOADING)
            assertThat(response.last().status).isEqualTo(ApiStatus.SUCCESS)
            assertThat(response.last().data?.size).isEqualTo(2)
        }
    }


}