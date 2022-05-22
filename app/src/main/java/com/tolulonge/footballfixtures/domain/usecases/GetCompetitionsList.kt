package com.tolulonge.footballfixtures.domain.usecases


import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionX
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import kotlinx.coroutines.flow.Flow

class GetCompetitionsList(
    private val repository: FootballFixturesRepository
) {

    operator fun invoke(fetchDataFromRemote: Boolean): Flow<Resource<List<DomainCompetitionX>>> {
        return repository.getCompetitionsList(fetchDataFromRemote)
    }
}
