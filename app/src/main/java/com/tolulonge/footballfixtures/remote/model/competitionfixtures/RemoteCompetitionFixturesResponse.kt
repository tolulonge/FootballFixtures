package com.tolulonge.footballfixtures.remote.model.competitionfixtures

data class RemoteCompetitionFixturesResponse(
    val competition: CompetitionXX?,
    val filters: Filters?,
    val matches: List<Matche>,
    val resultSet: ResultSet?
)