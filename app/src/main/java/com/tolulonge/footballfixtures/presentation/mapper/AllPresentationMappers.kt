package com.tolulonge.footballfixtures.presentation.mapper

/**
 * Mapper classes for converting domain data classes to the presentation layer data classes.
 */
class AllPresentationMappers(
    val domainTodayFixtureToPresentationTodayFixtureMapper: DomainTodayFixtureToPresentationTodayFixtureMapper,
    val domainCompetitionXToPresentationCompetitionXMapper: DomainCompetitionXToPresentationCompetitionXMapper,
    val domainCompetitionFixtureToPresentationCompetitionFixtureMapper: DomainCompetitionFixtureToPresentationCompetitionFixtureMapper
)