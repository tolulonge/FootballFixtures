package com.tolulonge.footballfixtures.remote.mapper

/**
 * These are mapper classes to help convert data classes from the remote layer to the data layer
 */
class AllRemoteMappers(
    val remoteTodayFixtureToDataTodayFixtureMapper: RemoteTodayFixtureToDataTodayFixtureMapper,
    val remoteCompetitionToDataCompetitionMapper: RemoteCompetitionToDataCompetitionMapper,
    val remoteCompetitionFixturesToDataCompetitionFixturesMapper: RemoteCompetitionFixturesToDataCompetitionFixturesMapper,

)