package com.tolulonge.footballfixtures.remote.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.remote.model.competitionfixtures.Matche

class RemoteCompetitionFixturesToDataCompetitionFixturesMapper :
    ListMapper<Matche, DataCompetitionFixture> {
    override fun map(input: List<Matche>): List<DataCompetitionFixture> = with(input) {
        map {
            DataCompetitionFixture(
                id = it.id,
                date = it.utcDate,
                status = it.status?.let { it1 -> MatchStatus.valueOf(it1) },
                homeTeamName = it.homeTeam?.name,
                awayTeamName = it.awayTeam?.name,
                homeTeamScore = it.score?.fullTime?.home,
                awayTeamScore = it.score?.fullTime?.away,
                homeTeamLogo = it.homeTeam?.crest,
                awayTeamLogo = it.awayTeam?.crest,
                competitionName = it.competition?.name,
                countryOfFixture = it.area?.name,
                refereeName = it.referees?.firstOrNull()?.name,
                matchDay = it.matchday,
                competitionCode = it.competition?.code,
                competitionEmblem = it.competition?.emblem,
                countryFlag = it.area?.flag
            )
        }
    }


}