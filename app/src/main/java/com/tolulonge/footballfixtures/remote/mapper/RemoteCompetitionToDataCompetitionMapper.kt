package com.tolulonge.footballfixtures.remote.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture
import com.tolulonge.footballfixtures.remote.model.competitions.CompetitionX
import com.tolulonge.footballfixtures.remote.model.todayfixture.Competition
import com.tolulonge.footballfixtures.remote.model.todayfixture.Match

class RemoteCompetitionToDataCompetitionMapper: ListMapper<CompetitionX,DataCompetitionX> {
    override fun map(input: List<CompetitionX>): List<DataCompetitionX> = with(input){
        map {
            DataCompetitionX(
                id = it.id,
                competitionName = it.name,
                currentMatchDay = it.currentSeason?.currentMatchday,
                nextMatchDay = it.currentSeason?.currentMatchday?.plus(1),
                competitionEmblem = it.emblem
            )
        }
    }


}