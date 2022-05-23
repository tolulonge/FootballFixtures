package com.tolulonge.footballfixtures.domain.usecases

/**
 * This are use_cases that are used to interact with the domain layer by the presentation layer
 */
class FootballFixturesUseCases(
    val getFootballFixtures: GetTodayFixtures,
    val getCompetitionsList: GetCompetitionsList,
    val getCompetitionFixturesList: GetCompetitionsFixturesList
)