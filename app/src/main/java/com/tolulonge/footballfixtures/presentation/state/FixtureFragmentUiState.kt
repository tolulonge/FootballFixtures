package com.tolulonge.footballfixtures.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class FixtureFragmentUiState {
    object Empty : FixtureFragmentUiState()
    data class Loading(val isLoading: Boolean) : FixtureFragmentUiState()
    data class Loaded(val data: List<PresentationTodayFixture>, val message: String) : FixtureFragmentUiState()
    data class Error(val message: String) : FixtureFragmentUiState()
}

@Parcelize
data class PresentationTodayFixture(
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
    val competitionEmblem: String?
):Parcelable


enum class MatchStatus {
    FINISHED,
    IN_PLAY,
    TIMED,
    PAUSED,
    UNAVAILABLE
}
