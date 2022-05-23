package com.tolulonge.footballfixtures.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.AllPresentationMappers
import com.tolulonge.footballfixtures.presentation.state.CompetitionsFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(
    private val footballFixturesUseCases: FootballFixturesUseCases,
    private val allPresentationMappers: AllPresentationMappers
) : ViewModel() {

    private val _competitionsList =
        MutableStateFlow<CompetitionsFragmentUiState>(CompetitionsFragmentUiState.Empty)
    val competitionsList = _competitionsList.asStateFlow()


    init {
        getCompetitionsList(false)
    }

    fun onEvent(event: FootballFixturesEvent) {
        when (event) {
            is FootballFixturesEvent.RefreshEvents -> {
                getCompetitionsList(fetchFromRemote = true)
            }

            else -> {}
        }
    }

    /**
     * Emits respective flow based on response from remote or database
     */
    private fun getCompetitionsList(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            footballFixturesUseCases.getCompetitionsList(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { competitions ->
                                _competitionsList.value = CompetitionsFragmentUiState.Loaded(
                                    allPresentationMappers.domainCompetitionXToPresentationCompetitionXMapper.map(
                                        competitions
                                    ),
                                    result.message ?: ""
                                )
                            }
                        }
                        is Resource.Error -> {
                            _competitionsList.value =
                                CompetitionsFragmentUiState.Error(result.message ?: "")
                            _competitionsList.value = CompetitionsFragmentUiState.Loading(false)

                        }
                        is Resource.Loading -> {
                            _competitionsList.value =
                                CompetitionsFragmentUiState.Loading(result.isLoading)
                        }
                    }
                }
        }
    }
}