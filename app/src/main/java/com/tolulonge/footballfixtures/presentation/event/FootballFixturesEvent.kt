package com.tolulonge.footballfixtures.presentation.event



sealed class FootballFixturesEvent {
    object RefreshEvents : FootballFixturesEvent()
    data class GetCompetitionFixtures(val competitionCode: String) : FootballFixturesEvent()
    data class RefreshCompetitionFixtures(val competitionCode: String) : FootballFixturesEvent()

}
