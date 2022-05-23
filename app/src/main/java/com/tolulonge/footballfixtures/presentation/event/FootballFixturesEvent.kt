package com.tolulonge.footballfixtures.presentation.event

/**
 * Event class to help handle UI interactions
 */
sealed class FootballFixturesEvent {
    object RefreshEvents : FootballFixturesEvent()
    data class GetCompetitionFixtures(val competitionCode: String) : FootballFixturesEvent()
    data class RefreshCompetitionFixtures(val competitionCode: String) : FootballFixturesEvent()
}
