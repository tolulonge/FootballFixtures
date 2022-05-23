package com.tolulonge.footballfixtures.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.AllPresentationMappers
import com.tolulonge.footballfixtures.presentation.state.FixtureFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val footballFixturesUseCases: FootballFixturesUseCases,
    private val allPresentationMappers: AllPresentationMappers
) : ViewModel() {

    private val _todayFixtures =
        MutableStateFlow<FixtureFragmentUiState>(FixtureFragmentUiState.Empty)
    val todayFixtures = _todayFixtures.asStateFlow()


    init {
        getTodayFixtures(false)
    }

    fun onEvent(event: FootballFixturesEvent) {
        when (event) {
            is FootballFixturesEvent.RefreshEvents -> {
                getTodayFixtures(fetchFromRemote = true)
            }
            else -> {}
        }
    }

    /**
     * Emits respective flow based on response from remote or database
     */
    private fun getTodayFixtures(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            footballFixturesUseCases.getFootballFixtures(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { fixtures ->
                                _todayFixtures.value = FixtureFragmentUiState.Loaded(
                                    allPresentationMappers.domainTodayFixtureToPresentationTodayFixtureMapper.map(
                                        fixtures
                                    ),
                                    result.message ?: ""
                                )
                            }
                        }
                        is Resource.Error -> {
                            _todayFixtures.value =
                                FixtureFragmentUiState.Error(result.message ?: "")
                            _todayFixtures.value = FixtureFragmentUiState.Loading(false)

                        }
                        is Resource.Loading -> {
                            _todayFixtures.value = FixtureFragmentUiState.Loading(result.isLoading)
                        }
                    }
                }
        }
    }
}