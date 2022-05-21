package com.tolulonge.footballfixtures.remote.model.todayfixture

data class Match(
    val area: Area?,
    val awayTeam: AwayTeam?,
    val competition: Competition?,
    val group: String?,
    val homeTeam: HomeTeam?,
    val id: Int?,
    val lastUpdated: String?,
    val referees: List<Referee>?,
    val score: Score?,
    val status: String?,
    val utcDate: String?
)