package com.tolulonge.footballfixtures.domain.repository


import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import kotlinx.coroutines.flow.Flow

interface FootballFixturesRepository {

    fun getTodayFixtures(fetchFromRemote: Boolean): Flow<Resource<List<DomainTodayFixture>>>

}
