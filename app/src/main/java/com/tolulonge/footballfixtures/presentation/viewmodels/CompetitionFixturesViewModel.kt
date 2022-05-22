package com.tolulonge.footballfixtures.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.domain.usecases.FootballFixturesUseCases
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.mapper.AllPresentationMappers
import com.tolulonge.footballfixtures.presentation.state.CompetitionFixturesFragmentUiState
import com.tolulonge.footballfixtures.presentation.state.FixtureFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionFixturesViewModel @Inject constructor(
    private val footballFixturesUseCases: FootballFixturesUseCases,
    private val allPresentationMappers: AllPresentationMappers
) : ViewModel() {

    private val _fixtures = MutableStateFlow<CompetitionFixturesFragmentUiState>(CompetitionFixturesFragmentUiState.Empty)
    val fixtures = _fixtures.asStateFlow()

    private var matchDay: Int = 0


    fun onEvent(event: FootballFixturesEvent) {
        when(event) {
            is FootballFixturesEvent.GetCompetitionFixtures -> {
                getMatchDayCompetitionFixtures(fetchFromRemote = false, event.competitionCode, matchDay)
            }
            is FootballFixturesEvent.RefreshCompetitionFixtures -> {
                getMatchDayCompetitionFixtures(fetchFromRemote = true, event.competitionCode,matchDay)

            }
            else -> {}
        }
    }

    private fun getMatchDayCompetitionFixtures(fetchFromRemote: Boolean, competitionCode: String, matchDay: Int) {
        viewModelScope.launch {
            footballFixturesUseCases.getCompetitionFixturesList(fetchFromRemote, competitionCode, matchDay)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { fixtures ->
                                if (fixtures.isEmpty()){
                                    _fixtures.value = CompetitionFixturesFragmentUiState.Empty
                                    return@collect
                                }
                                _fixtures.value = CompetitionFixturesFragmentUiState.Loaded(
                                   allPresentationMappers.domainCompetitionFixtureToPresentationCompetitionFixtureMapper.map(fixtures),
                                    result.message ?: ""
                                    )
                            }
                        }
                        is Resource.Error -> {
                            _fixtures.value = CompetitionFixturesFragmentUiState.Error(result.message ?: "")
                            _fixtures.value = CompetitionFixturesFragmentUiState.Loading(false)

                        }
                        is Resource.Loading -> {
                           _fixtures.value = CompetitionFixturesFragmentUiState.Loading(result.isLoading)
                        }
                    }
                }
        }
    }

    fun setMatchDay(i: Int) {
       matchDay = i
    }
}