package com.tolulonge.footballfixtures.presentation.state

data class PresentationTodayFixture(
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

sealed class FixtureFragmentUiState {
    object Empty : FixtureFragmentUiState()
    data class Loading(val isLoading: Boolean) : FixtureFragmentUiState()
    data class Loaded(val data: List<PresentationTodayFixture>, val message: String) : FixtureFragmentUiState()
    data class Error(val message: String) : FixtureFragmentUiState()
}