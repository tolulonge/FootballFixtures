package com.tolulonge.footballfixtures.domain.mapper

/**
 * This contains mapper classes to help convert data classes from the dataLayer to the domainLayer
 */
class AllDomainMappers(
    val dataCompetitionXToDomainCompetitionXMapper: DataCompetitionXToDomainCompetitionXMapper,
    val dataTodayFixtureToDomainTodayFixtureMapper: DataTodayFixtureToDomainTodayFixtureMapper,
    val dataCompetitionFixtureToDomainCompetitionFixtureMapper: DataCompetitionFixtureToDomainCompetitionFixtureMapper
)