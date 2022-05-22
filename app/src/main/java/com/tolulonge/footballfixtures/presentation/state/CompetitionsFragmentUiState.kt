package com.tolulonge.footballfixtures.presentation.state


sealed class CompetitionsFragmentUiState {
    object Empty : CompetitionsFragmentUiState()
    data class Loading(val isLoading: Boolean) : CompetitionsFragmentUiState()
    data class Loaded(val data: List<PresentationCompetitionX>, val message: String) : CompetitionsFragmentUiState()
    data class Error(val message: String) : CompetitionsFragmentUiState()
}

data class PresentationCompetitionX(
    val id: Int?,
    val competitionName: String?,
    val competitionEmblem: String?,
    val currentMatchDay: Int?,
    val nextMatchDay: Int?
)


