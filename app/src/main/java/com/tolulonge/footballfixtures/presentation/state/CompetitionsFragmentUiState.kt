package com.tolulonge.footballfixtures.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Sealed for that holds the state of the CompetitionsFragment
 */
sealed class CompetitionsFragmentUiState {
    object Empty : CompetitionsFragmentUiState()
    data class Loading(val isLoading: Boolean) : CompetitionsFragmentUiState()
    data class Loaded(val data: List<PresentationCompetitionX>, val message: String) : CompetitionsFragmentUiState()
    data class Error(val message: String) : CompetitionsFragmentUiState()
}

@Parcelize
data class PresentationCompetitionX(
    val id: Int?,
    val competitionName: String?,
    val competitionCountryName: String?,
    val competitionCode: String?,
    val competitionEmblem: String?,
    val competitionCountryEmblem: String?,
    val currentMatchDay: Int?,
    val nextMatchDay: Int?
):Parcelable


