package com.tolulonge.footballfixtures.remote.model.competitions

import com.tolulonge.footballfixtures.remote.model.competitionfixtures.AreaX


data class CompetitionX(
    val currentSeason: CurrentSeason?,
    val emblem: String?,
    val id: Int?,
    val lastUpdated: String?,
    val name: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?,
    val type: String?,
    val area: AreaX,
    val code: String?,
)