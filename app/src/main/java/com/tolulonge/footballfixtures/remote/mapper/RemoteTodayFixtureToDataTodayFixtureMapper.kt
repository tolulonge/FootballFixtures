package com.tolulonge.footballfixtures.remote.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.remote.model.todayfixture.Match

class RemoteTodayFixtureToDataTodayFixtureMapper: ListMapper<Match,DataTodayFixture> {
    override fun map(input: List<Match>): List<DataTodayFixture> = with(input){
        map {
            DataTodayFixture(
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
                refereeName = it.referees?.firstOrNull()?.name
            )
        }
    }


}