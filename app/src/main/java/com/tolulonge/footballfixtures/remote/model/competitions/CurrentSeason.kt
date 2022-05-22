package com.tolulonge.footballfixtures.remote.model.competitions

data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String?,
    val id: Int?,
    val startDate: String?,
    val winner: Any?
)