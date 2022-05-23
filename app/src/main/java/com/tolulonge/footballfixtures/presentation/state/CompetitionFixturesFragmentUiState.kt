package com.tolulonge.footballfixtures.presentation.state


/**
 * Sealed for that holds the state of the CompetitionFixturesFragment
 */
sealed class CompetitionFixturesFragmentUiState {
    object Empty : CompetitionFixturesFragmentUiState()
    data class Loading(val isLoading: Boolean) : CompetitionFixturesFragmentUiState()
    data class Loaded(val data: List<PresentationCompetitionFixture>, val message: String) : CompetitionFixturesFragmentUiState()
    data class Error(val message: String) : CompetitionFixturesFragmentUiState()
}

data class PresentationCompetitionFixture(
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
    val countryFlag: String?,
    val competitionEmblem: String?,
    val elapsedTime: String? = null
)

fun PresentationCompetitionFixture.toPresentationTodayFixture(): PresentationTodayFixture {
    return PresentationTodayFixture(
        id = id,
        date = date,
        status = status,
        homeTeamName = homeTeamName,
        awayTeamName = awayTeamName,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore,
        homeTeamLogo = homeTeamLogo,
        awayTeamLogo = awayTeamLogo,
        countryOfFixture = countryOfFixture,
        competitionName = competitionName,
        refereeName = refereeName,
        countryFlag = countryFlag,
        competitionEmblem = competitionEmblem
    )
}

