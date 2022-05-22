package com.tolulonge.footballfixtures.domain.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionX
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture

class DataCompetitionXToDomainCompetitionXMapper: ListMapper<DataCompetitionX, DomainCompetitionX> {
    override fun map(input: List<DataCompetitionX>): List<DomainCompetitionX> = with(input){
        map {
            DomainCompetitionX(
                id = it.id,
                competitionName = it.competitionName,
                competitionEmblem = it.competitionEmblem,
                currentMatchDay = it.currentMatchDay,
                nextMatchDay = it.nextMatchDay,
                competitionCode = it.competitionCode,
                competitionCountryEmblem = it.competitionCountryEmblem,
                competitionCountryName = it.competitionCountryName
            )
        }
    }
}