package com.tolulonge.footballfixtures.domain.repository


import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionFixture
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionX
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

interface FootballFixturesRepository {

    fun getTodayFixtures(fetchFromRemote: Boolean): Flow<Resource<List<DomainTodayFixture>>>

    fun getCompetitionsList(fetchFromRemote: Boolean): Flow<Resource<List<DomainCompetitionX>>>

    fun getCompetitionFixtures(fetchFromRemote: Boolean,competitionCode: String, matchDay: Int): Flow<Resource<List<DomainCompetitionFixture>>>


    suspend fun <E, T> FlowCollector<Resource<List<E>>>.isFetchingResultFromDb(
        fetchFromRemote: Boolean,
        localData: List<T>,
        retrieveFromDb: suspend () -> List<E>
    ): Boolean

    suspend fun <E, T> FlowCollector<Resource<List<E>>>.retrieveContentFromRemote(response: Resource<List<T>>): List<T>?
    suspend fun <E, T> FlowCollector<Resource<List<E>>>.updateLocalFromRemoteAndEmitResult(
        remoteResult: List<T>?,
        insertToDb: suspend (List<T>) -> Unit,
        retrieveFromDb: suspend () -> List<E>
    )

    fun <T> checkIfDatabaseIsEmpty(localData: List<T>): Boolean
}
