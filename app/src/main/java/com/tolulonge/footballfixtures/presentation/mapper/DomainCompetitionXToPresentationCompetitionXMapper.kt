package com.tolulonge.footballfixtures.presentation.mapper

import com.tolulonge.footballfixtures.core.mapper.ListMapper
import com.tolulonge.footballfixtures.domain.model.DomainCompetitionX
import com.tolulonge.footballfixtures.domain.model.DomainTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.presentation.state.PresentationCompetitionX
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class DomainCompetitionXToPresentationCompetitionXMapper : ListMapper<DomainCompetitionX,PresentationCompetitionX> {
    override fun map(input: List<DomainCompetitionX>): List<PresentationCompetitionX> =
        with(input){
            map {
                PresentationCompetitionX(
                   id = it.id,
                   competitionEmblem = it.competitionEmblem,
                   competitionName = it.competitionName,
                   currentMatchDay = it.currentMatchDay,
                    nextMatchDay = it.nextMatchDay,
                )
            }
        }
}