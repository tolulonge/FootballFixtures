package com.tolulonge.footballfixtures.remote.model.competitionfixtures

data class Score(
    val duration: String?,
    val fullTime: FullTime?,
    val halfTime: HalfTime?,
    val winner: String?
)