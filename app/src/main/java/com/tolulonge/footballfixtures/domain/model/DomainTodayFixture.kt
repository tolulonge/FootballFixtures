package com.tolulonge.footballfixtures.domain.model

import com.tolulonge.footballfixtures.presentation.state.MatchStatus

data class DomainTodayFixture(
    val id: Int?,
    val date: String?,
    val status: MatchStatus?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?,
    val homeTeamLogo: String?,
    val awayTeamLogo: String?,
    val countryOfFixture: String?,
    val competitionName: String?,
    val refereeName: String?,
    val competitionEmblem: String?,
    val countryFlag: String?,
    val elapsedTime: String? = null
)
