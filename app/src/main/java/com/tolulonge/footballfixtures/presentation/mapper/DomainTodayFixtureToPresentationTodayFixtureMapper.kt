package com.tolulonge.footballfixtures.presentation.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class DomainTodayFixtureToPresentationTodayFixtureMapper :
    ListMapper<DomainTodayFixture, PresentationTodayFixture> {
    override fun map(input: List<DomainTodayFixture>): List<PresentationTodayFixture> =
        with(input) {
            map {
                PresentationTodayFixture(
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
                    refereeName = it.refereeName,
                    competitionEmblem = it.competitionEmblem,
                    countryFlag = it.countryFlag
                )
            }
        }
}