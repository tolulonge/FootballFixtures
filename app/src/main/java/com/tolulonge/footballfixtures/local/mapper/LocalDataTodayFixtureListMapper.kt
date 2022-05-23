package com.tolulonge.footballfixtures.local.mapper

import com.tolulonge.footballfixtures.core.mapper.ToAndFroListMapper
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus

class LocalDataTodayFixtureListMapper: ToAndFroListMapper<LocalTodayFixture, DataTodayFixture> {

    override fun from(e: List<DataTodayFixture>): List<LocalTodayFixture> {
        return e.map { toLocal(it) }
    }

    override fun to(t: List<LocalTodayFixture>): List<DataTodayFixture> {
        return t.map { toData(it) }
    }

    private fun toData(from: LocalTodayFixture): DataTodayFixture {
        return DataTodayFixture(
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
            competitionEmblem = from.competitionEmblem,
            countryFlag = from.countryFlag
        )
    }

    private fun toLocal(from: DataTodayFixture): LocalTodayFixture {
        return LocalTodayFixture(
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
            competitionEmblem = from.competitionEmblem,
            countryFlag = from.countryFlag
        )
    }




}