package com.tolulonge.footballfixtures.domain.usecases

class FootballFixturesUseCases(
    val getFootballFixtures: GetTodayFixtures,
    val getCompetitionsList: GetCompetitionsList,
    val getCompetitionFixturesList: GetCompetitionsFixturesList
)