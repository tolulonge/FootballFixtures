package com.tolulonge.footballfixtures.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.DomainTodayFixtureToPresentationTodayFixtureMapper
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FixtureViewModel(
    private val footballFixturesUseCases: FootballFixturesUseCases,
    private val domainTodayFixtureToPresentationTodayFixtureMapper: DomainTodayFixtureToPresentationTodayFixtureMapper
) : ViewModel() {

    private val _todayFixtures = MutableStateFlow<List<PresentationTodayFixture>>(emptyList())
    val todayFixtures = _todayFixtures.asStateFlow()

    init {
        getTodayFixtures()
    }

    fun onEvent(event: FootballFixturesEvent) {
        when(event) {
            is FootballFixturesEvent.RefreshFixtures -> {
                getTodayFixtures(fetchFromRemote = true)
            }

        }
    }

    private fun getTodayFixtures(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            footballFixturesUseCases.getFootballFixtures(fetchFromRemote)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { fixtures ->
                                _todayFixtures.value = domainTodayFixtureToPresentationTodayFixtureMapper.map(fixtures)
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {

                        }
                    }
                }
        }
    }
}