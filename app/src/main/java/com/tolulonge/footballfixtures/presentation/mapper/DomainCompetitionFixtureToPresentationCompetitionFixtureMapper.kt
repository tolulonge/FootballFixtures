package com.tolulonge.footballfixtures.presentation.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionFixture
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.presentation.state.PresentationCompetitionFixture
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class DomainCompetitionFixtureToPresentationCompetitionFixtureMapper : ListMapper<DomainCompetitionFixture,PresentationCompetitionFixture> {
    override fun map(input: List<DomainCompetitionFixture>): List<PresentationCompetitionFixture> =
        with(input){
            map {
                PresentationCompetitionFixture(
                   id = it.id,
                   date = it.date,
                   status = it.status,
                   homeTeamName = it.homeTeamName,
                   awayTeamName = it.awayTeamName,
                   homeTeamScore = it.homeTeamScore,
                   awayTeamScore = it.awayTeamScore,
                   homeTeamLogo = it.homeTeamLogo,
                   awayTeamLogo = it.awayTeamLogo,
                   countryOfFixture = it.countryOfFixture,
                   competitionName = it.competitionName,
                   refereeName = it.refereeName
                )
            }
        }
}