package com.tolulonge.footballfixtures.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.DomainTodayFixtureToPresentationTodayFixtureMapper
import com.tolulonge.footballfixtures.presentation.state.FixtureFragmentUiState
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val footballFixturesUseCases: FootballFixturesUseCases,
    private val domainTodayFixtureToPresentationTodayFixtureMapper: DomainTodayFixtureToPresentationTodayFixtureMapper
) : ViewModel() {

    private val _todayFixtures = MutableStateFlow<FixtureFragmentUiState>(FixtureFragmentUiState.Empty)
    val todayFixtures = _todayFixtures.asStateFlow()


    init {
        getTodayFixtures(false)
    }

    fun onEvent(event: FootballFixturesEvent) {
        when(event) {
            is FootballFixturesEvent.RefreshFixtures -> {
                getTodayFixtures(fetchFromRemote = true)
            }

        }
    }

    private fun getTodayFixtures(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            footballFixturesUseCases.getFootballFixtures(fetchFromRemote)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { fixtures ->
                                _todayFixtures.value = FixtureFragmentUiState.Loaded(
                                    domainTodayFixtureToPresentationTodayFixtureMapper.map(fixtures),
                                    result.message ?: ""
                                    )
                            }
                        }
                        is Resource.Error -> {
                            _todayFixtures.value = FixtureFragmentUiState.Error(result.message ?: "")
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