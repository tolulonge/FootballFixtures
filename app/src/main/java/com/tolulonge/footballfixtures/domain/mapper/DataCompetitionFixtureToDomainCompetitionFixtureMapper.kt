package com.tolulonge.footballfixtures.domain.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionFixture

class DataCompetitionFixtureToDomainCompetitionFixtureMapper: ListMapper<DataCompetitionFixture, DomainCompetitionFixture> {
    override fun map(input: List<DataCompetitionFixture>): List<DomainCompetitionFixture> = with(input){
        map {
            DomainCompetitionFixture(
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
                refereeName = it.refereeName,
                competitionEmblem = it.competitionEmblem,
                countryFlag = it.countryFlag
            )
        }
    }
}