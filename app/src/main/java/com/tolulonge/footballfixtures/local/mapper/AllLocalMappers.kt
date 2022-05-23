package com.tolulonge.footballfixtures.local.mapper

/**
 * These are mapper classes to help convert data classes from the local layer to the data layer and
 * vice versa
 */
class AllLocalMappers(
    val localDataTodayFixtureListMapper: LocalDataTodayFixtureListMapper,
    val localDataCompetitionXListMapper: LocalDataCompetitionXListMapper,
    val localDataCompetitionFixtureListMapper: LocalDataCompetitionFixtureListMapper
)