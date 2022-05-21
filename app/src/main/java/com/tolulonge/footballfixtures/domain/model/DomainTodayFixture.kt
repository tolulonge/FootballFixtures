package com.tolulonge.footballfixtures.domain.model

data class DomainTodayFixture(
    val id: Int?,
    val date: String?,
    val status: String?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?,
    val homeTeamLogo: String?,
    val awayTeamLogo: String?,
    val countryOfFixture: String?,
    val competitionName: String?,
    val refereeName: String?
)
