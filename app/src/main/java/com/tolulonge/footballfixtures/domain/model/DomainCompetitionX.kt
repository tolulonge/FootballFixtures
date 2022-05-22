package com.tolulonge.footballfixtures.domain.model


data class DomainCompetitionX(
    val id: Int?,
    val competitionName: String?,
    val competitionEmblem: String?,
    val currentMatchDay: Int?,
    val nextMatchDay: Int?,
    val competitionCountryName: String?,
    val competitionCode: String?,
    val competitionCountryEmblem: String?
)