package com.tolulonge.footballfixtures.remote.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.remote.model.competitions.CompetitionX

class RemoteCompetitionToDataCompetitionMapper : ListMapper<CompetitionX, DataCompetitionX> {
    override fun map(input: List<CompetitionX>): List<DataCompetitionX> = with(input) {
        map {
            DataCompetitionX(
                id = it.id,
                competitionName = it.name,
                currentMatchDay = it.currentSeason?.currentMatchday,
                nextMatchDay = it.currentSeason?.currentMatchday?.plus(1),
                competitionEmblem = it.emblem,
                competitionCountryEmblem = it.area.flag,
                competitionCode = it.code,
                competitionCountryName = it.area.name
            )
        }
    }


}