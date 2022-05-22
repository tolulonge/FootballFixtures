package com.tolulonge.footballfixtures.remote.model.competitions

data class CompetitionX(
    val currentSeason: CurrentSeason?,
    val emblem: String?,
    val id: Int?,
    val lastUpdated: String?,
    val name: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?,
    val type: String?
)