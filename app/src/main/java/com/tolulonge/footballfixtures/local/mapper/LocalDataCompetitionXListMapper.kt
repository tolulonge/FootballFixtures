package com.tolulonge.footballfixtures.local.mapper

import com.tolulonge.footballfixtures.core.mapper.ToAndFroListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.local.model.LocalCompetitionX
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture

class LocalDataCompetitionXListMapper: ToAndFroListMapper<LocalCompetitionX, DataCompetitionX> {

    override fun from(e: List<DataCompetitionX>): List<LocalCompetitionX> {
        return e.map { toLocal(it) }
    }

    override fun to(t: List<LocalCompetitionX>): List<DataCompetitionX> {
        return t.map { toData(it) }
    }

    private fun toData(from: LocalCompetitionX): DataCompetitionX {
        return DataCompetitionX(
            id = from.id,
            competitionName = from.competitionName,
            competitionEmblem = from.competitionEmblem,
            currentMatchDay = from.currentMatchDay,
            nextMatchDay = from.nextMatchDay
        )
    }

    private fun toLocal(from: DataCompetitionX): LocalCompetitionX {
        return LocalCompetitionX(
            id = from.id,
            competitionName = from.competitionName,
            competitionEmblem = from.competitionEmblem,
            currentMatchDay = from.currentMatchDay,
            nextMatchDay = from.nextMatchDay
        )
    }




}