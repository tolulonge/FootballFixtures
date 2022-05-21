package com.tolulonge.footballfixtures.domain.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture

class DataTodayFixtureToDomainTodayFixtureMapper: ListMapper<DataTodayFixture, DomainTodayFixture> {
    override fun map(input: List<DataTodayFixture>): List<DomainTodayFixture> = with(input){
        map {
            DomainTodayFixture(
                id = it.id,
                date = it.date,
                status = it.status,
                homeTeamName = it.homeTeamName,
                awayTeamName = it.awayTeamName,
                homeTeamScore = it.homeTeamScore,
                awayTeamScore = it.awayTeamScore,
                homeTeamLogo = it.homeTeamLogo,
                awayTeamLogo = it.awayTeamLogo,
                competitionName = it.competitionName,
                countryOfFixture = it.countryOfFixture,
                refereeName = it.refereeName
            )
        }
    }
}