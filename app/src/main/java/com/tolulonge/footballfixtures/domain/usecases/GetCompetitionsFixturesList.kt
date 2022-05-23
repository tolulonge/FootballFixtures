package com.tolulonge.footballfixtures.domain.usecases


import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionFixture
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import kotlinx.coroutines.flow.Flow

class GetCompetitionsFixturesList(
    private val repository: FootballFixturesRepository
) {

    operator fun invoke(fetchDataFromRemote: Boolean,competitionCode: String, matchDay: Int): Flow<Resource<List<DomainCompetitionFixture>>> {
        return repository.getCompetitionFixtures(fetchDataFromRemote,competitionCode, matchDay)
    }
}
