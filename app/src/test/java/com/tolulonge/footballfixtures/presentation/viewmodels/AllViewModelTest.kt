package com.tolulonge.footballfixtures.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.tolulonge.footballfixtures.data.repository.FootballFixturesRepositoryImpl
import com.tolulonge.footballfixtures.data.repository.LocalDataSource
import com.tolulonge.footballfixtures.data.repository.RemoteDataSource
import com.tolulonge.footballfixtures.domain.mapper.AllDomainMappers
import com.tolulonge.footballfixtures.domain.mapper.DataCompetitionFixtureToDomainCompetitionFixtureMapper
import com.tolulonge.footballfixtures.domain.mapper.DataCompetitionXToDomainCompetitionXMapper
import com.tolulonge.footballfixtures.domain.mapper.DataTodayFixtureToDomainTodayFixtureMapper
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.domain.usecases.GetCompetitionsFixturesList
import com.tolulonge.footballfixtures.domain.usecases.GetCompetitionsList
import com.tolulonge.footballfixtures.domain.usecases.GetTodayFixtures
import com.tolulonge.footballfixtures.local.FakeDatabase
import com.tolulonge.footballfixtures.local.FakeLocalDataSourceImpl
import com.tolulonge.footballfixtures.local.mapper.AllLocalMappers
import com.tolulonge.footballfixtures.local.mapper.LocalDataCompetitionFixtureListMapper
import com.tolulonge.footballfixtures.local.mapper.LocalDataCompetitionXListMapper
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.AllPresentationMappers
import com.tolulonge.footballfixtures.presentation.mapper.DomainCompetitionFixtureToPresentationCompetitionFixtureMapper
import com.tolulonge.footballfixtures.presentation.mapper.DomainCompetitionXToPresentationCompetitionXMapper
import com.tolulonge.footballfixtures.presentation.mapper.DomainTodayFixtureToPresentationTodayFixtureMapper
import com.tolulonge.footballfixtures.remote.FakeRemoteDataSourceImpl
import com.tolulonge.footballfixtures.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AllViewModelTest {

    private lateinit var fixtureViewModel: FixtureViewModel
    private lateinit var competitionsViewModel: CompetitionsViewModel
    private lateinit var competitionFixturesViewModel: CompetitionFixturesViewModel
    private lateinit var repository: FootballFixturesRepository
    private lateinit var allDomainMappers: AllDomainMappers
    private lateinit var allLocalMappers: AllLocalMappers
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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

        val allPresentationMappers = AllPresentationMappers(
            domainCompetitionFixtureToPresentationCompetitionFixtureMapper = DomainCompetitionFixtureToPresentationCompetitionFixtureMapper(),
            domainTodayFixtureToPresentationTodayFixtureMapper = DomainTodayFixtureToPresentationTodayFixtureMapper(),
            domainCompetitionXToPresentationCompetitionXMapper = DomainCompetitionXToPresentationCompetitionXMapper()
        )
        val getTodayFixturesUseCases = GetTodayFixtures(
            repository
        )
        val getCompetitionsList = GetCompetitionsList(
            repository
        )
        val getCompetitionsFixturesList = GetCompetitionsFixturesList(
            repository
        )
        fixtureViewModel = FixtureViewModel(
           allPresentationMappers = allPresentationMappers,
            footballFixturesUseCases = FootballFixturesUseCases(
                getFootballFixtures = getTodayFixturesUseCases,
                getCompetitionsList = getCompetitionsList,
                getCompetitionsFixturesList
            )
        )
        competitionsViewModel = CompetitionsViewModel(
            allPresentationMappers = allPresentationMappers,
            footballFixturesUseCases = FootballFixturesUseCases(
                getFootballFixtures = getTodayFixturesUseCases,
                getCompetitionsList = getCompetitionsList,
                getCompetitionFixturesList = getCompetitionsFixturesList
            )
        )
        competitionFixturesViewModel = CompetitionFixturesViewModel(
            allPresentationMappers = allPresentationMappers,
            footballFixturesUseCases = FootballFixturesUseCases(
                getFootballFixtures = getTodayFixturesUseCases,
                getCompetitionsList = getCompetitionsList,
                getCompetitionFixturesList = getCompetitionsFixturesList
            )
        )
    }

    @Test
    fun `refresh today fixtures from remote, returns fresh updates from remote and updates database`(){
        runBlocking {
            val oldDbSize = localDataSource.getTodayFixturesDb().size
            val event = FootballFixturesEvent.RefreshEvents
            fixtureViewModel.onEvent(event)
            val newDbSize = localDataSource.getTodayFixturesDb().size
            assertThat(newDbSize > oldDbSize).isTrue()
            val topItemInDb = localDataSource.getTodayFixturesDb().first()
            assertThat(topItemInDb.awayTeamName).isEqualTo("Galatasaray")
        }
    }

    @Test
    fun `refresh competition list from remote, returns fresh updates from remote and updates database`(){
        runBlocking {
            val oldDbSize = localDataSource.getCompetitionsList().size
            val event = FootballFixturesEvent.RefreshEvents
            competitionsViewModel.onEvent(event)
            val newDbSize = localDataSource.getCompetitionsList().size
            assertThat(newDbSize > oldDbSize).isTrue()
            val result = localDataSource.getCompetitionsList()
            assertThat(result.size).isEqualTo(6)
        }
    }

    @Test
    fun `refresh competition fixtures list from remote, returns fresh updates from remote and updates database`(){
        runBlocking {
            val matchDay = 38
            val competitionCode = "PL"
            competitionFixturesViewModel.setMatchDay(matchDay)
            val oldDbSize = localDataSource.getCompetitionFixtures(matchDay, competitionCode).size
            val event = FootballFixturesEvent.RefreshCompetitionFixtures(competitionCode)
            competitionFixturesViewModel.onEvent(event)
            val newDbSize = localDataSource.getCompetitionFixtures(matchDay,competitionCode).size
            assertThat(newDbSize > oldDbSize).isTrue()
            val result = localDataSource.getCompetitionFixtures(matchDay,competitionCode)
            assertThat(result.size).isEqualTo(2)
        }
    }
}