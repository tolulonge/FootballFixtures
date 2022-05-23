package com.tolulonge.footballfixtures.local.mapper

import com.tolulonge.footballfixtures.core.mapper.ToAndFroListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.local.model.LocalCompetitionFixture
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus

class LocalDataCompetitionFixtureListMapper: ToAndFroListMapper<LocalCompetitionFixture, DataCompetitionFixture> {

    override fun from(e: List<DataCompetitionFixture>): List<LocalCompetitionFixture> {
        return e.map { toLocal(it) }
    }

    override fun to(t: List<LocalCompetitionFixture>): List<DataCompetitionFixture> {
        return t.map { toData(it) }
    }

    private fun toData(from: LocalCompetitionFixture): DataCompetitionFixture {
        return DataCompetitionFixture(
            id = from.id,
            date = from.date,
            status = from.status?.let { it1 -> MatchStatus.valueOf(it1) },
            homeTeamName = from.homeTeamName,
            awayTeamName =  from.awayTeamName,
            homeTeamScore = from.homeTeamScore,
            awayTeamScore = from.awayTeamScore,
            awayTeamLogo = from.awayTeamLogo,
            homeTeamLogo = from.homeTeamLogo,
            countryOfFixture = from.countryOfFixture,
            competitionName = from.competitionName,
            refereeName = from.refereeName,
            matchDay = from.matchDay,
            competitionCode = from.competitionCode,
            competitionEmblem = from.competitionEmblem,
            countryFlag = from.countryFlag
        )
    }

    private fun toLocal(from: DataCompetitionFixture): LocalCompetitionFixture {
        return LocalCompetitionFixture(
            id = from.id,
            date = from.date,
            status = from.status?.name,
            homeTeamName = from.homeTeamName,
            awayTeamName =  from.awayTeamName,
            homeTeamScore = from.homeTeamScore,
            awayTeamScore = from.awayTeamScore,
            awayTeamLogo = from.awayTeamLogo,
            homeTeamLogo = from.homeTeamLogo,
            countryOfFixture = from.countryOfFixture,
            competitionName = from.competitionName,
            refereeName = from.refereeName,
            matchDay = from.matchDay,
            competitionCode = from.competitionCode,
            competitionEmblem = from.competitionEmblem,
            countryFlag = from.countryFlag
        )
    }




}